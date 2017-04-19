package newengine.trigger;

import java.util.List;

import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;
import bus.EventBus;
import newengine.events.HasTriggeringSprite;
import newengine.events.trigger.SpriteTriggerActionEvent;
import newengine.events.trigger.SpriteTriggerRegisterEvent;
import newengine.sprite.SpriteID;
import newengine.trigger.TriggerAction.TriggerActionType;
import newengine.trigger.TriggerEvent.TriggerEventType;
import newengine.utils.variable.VarMap;

public class TriggerManager {

	private VarMap map; // TODO
	private EventBus bus;

	public TriggerManager(EventBus bus) {
		this.bus = bus;
	}

	public void registerTrigger(Trigger trigger) {
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

	public BusEventHandler<BusEvent> genHandler(List<TriggerCondition> conditions, List<TriggerAction> actions) {
		return new BusEventHandler<BusEvent>() {
			@Override
			public void handle(BusEvent event) {
				for (TriggerCondition condition : conditions) {
					if (!condition.isTrue(map)) {
						return;
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