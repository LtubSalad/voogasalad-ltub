package newengine.trigger;

import java.util.List;

import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;
import bus.EventBus;
import newengine.events.HasTriggeringSprite;
import newengine.events.trigger.AddTriggerEvent;
import newengine.events.trigger.SpriteTriggerActionEvent;
import newengine.events.trigger.SpriteTriggerRegisterEvent;
import newengine.model.Models;
import newengine.sprite.SpriteID;
import newengine.trigger.TriggerAction.TriggerActionType;
import newengine.trigger.TriggerEvent.TriggerEventType;

public class TriggerManager {

	private Models models;
	private EventBus bus;

	public TriggerManager(EventBus bus, Models models) {
		this.bus = bus;
		this.models = models;
		initHandlers();
	}

	private void initHandlers() {
		bus.on(AddTriggerEvent.ADD, (e) -> {
			registerTrigger(e.getTrigger());
		});
	}
	
	private void registerTrigger(Trigger trigger) {
		TriggerEvent triggerEvent = trigger.getEvent();
		List<TriggerCondition> conditions = trigger.getConditions();
		List<TriggerAction> actions = trigger.getActions();

		BusEventType<? extends BusEvent> triggerBusEventType = triggerEvent.getBusEventType();
		BusEventHandler<BusEvent> handler = genHandler(conditions, actions);

		if (triggerEvent.getType() == TriggerEventType.GAME) {
			bus.on(triggerBusEventType, handler);
		} else if (triggerEvent.getType() == TriggerEventType.SPRITE_ALL) {
			bus.emit(SpriteTriggerRegisterEvent.spriteAllRegisterEvent(triggerBusEventType, handler));
		} else if (triggerEvent.getType() == TriggerEventType.SPRITE_SPECIFIC) {
			SpriteID spriteID = triggerEvent.getSpriteID();
			if (spriteID != null) {
				bus.emit(SpriteTriggerRegisterEvent.spriteSpecificRegisterEvent(triggerBusEventType, handler, spriteID));
			}
		}
	}

	private BusEventHandler<BusEvent> genHandler(List<TriggerCondition> conditions, List<TriggerAction> actions) {
		return new BusEventHandler<BusEvent>() {
			@Override
			public void handle(BusEvent event) {
				for (TriggerCondition condition : conditions) {
					if (event instanceof HasTriggeringSprite) {
						if (!condition.isTrue(models, ((HasTriggeringSprite) event).getSprite())) {
							return;
						}
					} else {
						if (!condition.isTrue(models, null)) {
							return;
						}
					}

				}
				for (TriggerAction action : actions) {
					if (action.getType() == TriggerActionType.GAME) {
						bus.emit(action.getBusEvent());
					} else if (action.getType() == TriggerActionType.SPRITE_BROADCAST) {
						bus.emit(SpriteTriggerActionEvent.createBroadcastEvent(action.getBusEvent()));
					} else if (action.getType() == TriggerActionType.SPRITE_SPECIFIC) {
						bus.emit(SpriteTriggerActionEvent.createSpecificEvent(action.getBusEvent(), action.getSpriteID()));
					} else if (action.getType() == TriggerActionType.SPRITE_TRIGGERING_UNIT) {
						if (event instanceof HasTriggeringSprite) {
							SpriteID spriteID = ((HasTriggeringSprite) event).getSprite().getID();
							bus.emit(SpriteTriggerActionEvent.createSpecificEvent(action.getBusEvent(), spriteID));
						}

					}
				}
			}
		};
	}

}