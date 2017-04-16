package newengine.trigger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;
import bus.EventBus;
import newengine.Model;
import newengine.event.MoveEvent;
import newengine.sprite.Sprite;

public class TriggerManager {

	Model model;
	EventBus bus;

	private BusEvent getAction(String actionName) {
		return null;
	}

	private <T extends BusEvent> BusEventType<T> getEventType(String eventName) {
		return null;
	}
	
	public void processTrigger(Trigger trigger) {
		String eventName = trigger.getEvent();
		Optional<SpriteID> spriteID = trigger.getSpriteID();
		List<String> conditions = trigger.getConditions(); // TODO
		List<String> actions = trigger.getActions();
		processTrigger(getEventType(eventName), spriteID, actions);
	}
	
	private <T extends BusEvent> void processTrigger(BusEventType<T> eventType, Optional<SpriteID> spriteID, List<String> actions) {
		List<EventBus> receivers = getReceivers(eventType, spriteID);
		for (EventBus receiverBus : receivers) {
			receiverBus.on(eventType, genHandler(eventType, actions));
		}
	}
	
	private <T extends BusEvent> List<EventBus> getReceivers(BusEventType<T> busEventType, Optional<SpriteID> spriteID) {
		List<EventBus> busList = new ArrayList<>();
		if (busEventType == MoveEvent.SPECIFIC) {
			if (spriteID.isPresent()) {
				busList.add(model.getSpriteByID(spriteID.get()).getSpriteBus());
			} 
		} else if (busEventType == GameSaveEvent.ANY) {
			busList.add(bus);
		} else if (busEventType == MoveEvent.ALL) {
			for (Sprite sprite : model.getSprites()) {
				busList.add(sprite.getSpriteBus());
			}
		}
		return busList;
	}
	
	public <T extends BusEvent> BusEventHandler<T> genHandler(BusEventType<T> eventType, List<String> actions) {
		return new BusEventHandler<T>() { // ??? TODO
			@Override
			public void handle(BusEvent event) {
				for (String action : actions) {
					if (action.equals("should be given to bus")) {
						bus.emit(getAction(action));
					} else if (action.equals("should be given to each sprite")) {
						for (Sprite sprite : model.getSprites()) {
							sprite.emit(getAction(action));
						}
					} else if (action.equals("specific sprite action")) {
						model.getSpriteByID(new SpriteID("spriteID")).emit(getAction(action));
					}
				}
			}
		};
	}

}
