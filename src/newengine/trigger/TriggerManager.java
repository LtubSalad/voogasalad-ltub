package newengine.trigger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bus.BasicEventBus;
import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;
import bus.EventBus;
import newengine.events.game.GameSaveEvent;
import newengine.events.sprite.MoveEvent;
import newengine.model.SpriteModel;
import newengine.sprite.Sprite;
import newengine.sprite.SpriteID;
import newengine.utils.variable.VarMap;

public class TriggerManager {

//	/**
//	 * A map for true key-value pairs for conditions in triggers.
//	 */
//	VarMap map; 
//	SpriteModel model;
//	EventBus bus;
//	
//	public TriggerManager(EventBus bus, SpriteModel model) {
//		this.bus = bus;
//		this.model = model;
//	}
//
////	private <T extends BusEvent> BusEventType<T> getEventType(BusEvent event) {
////		return event.getEventType();
////	}
//	
//	public void processTrigger(Trigger trigger) {
//		BusEventType<?> triggerEventType = trigger.getEventType();
//		
//		Optional<SpriteID> spriteID = trigger.getSpriteID(); // TODO 
//		List<Condition> conditions = trigger.getConditions(); // TODO
////		List<String> actions = trigger.getActions();
////		List<EventBus> receivers = getReceivers(eventType, spriteID);
////		for (EventBus receiverBus : receivers) {
////			receiverBus.on(eventType, genHandler(eventType, conditions, actions));
////		}
//		BusEventType<BusEvent> type1 = new BusEventType<>("type1");
//		EventBus triggerBus = new BasicEventBus();
//		getReceivers.on(MoveEvent.ALL, (e) -> {
//			triggerBus.emit(new UserDefinedEvent(type1, ...));
//		});
//		
//		triggerBus.on(type1, (e) -> {
//			actions...
//		});
//	}
//	
//	private <T extends BusEvent> List<EventBus> getReceivers(BusEventType<T> busEventType, Optional<SpriteID> spriteID) {
//		List<EventBus> busList = new ArrayList<>();
//		if (busEventType == MoveEvent.SPECIFIC) {
//			if (spriteID.isPresent()) {
//				busList.add(model.getByID(spriteID.get()).get().getSpriteBus());
//			} 
//		} else if (busEventType == GameSaveEvent.ANY) {
//			busList.add(bus);
//		} else if (busEventType == MoveEvent.ALL) {
//			for (Sprite sprite : model.getSprites()) {
//				busList.add(sprite.getSpriteBus());
//			}
//		}
//		return busList;
//	}
//	
//	public <T extends BusEvent> BusEventHandler<T> genHandler(BusEventType<T> eventType, List<Condition> conditions, List<String> actions) {
//		return new BusEventHandler<T>() { // ??? TODO
//			@Override
//			public void handle(BusEvent event) {
//				for (Condition condition : conditions) {
//					if (!condition.isTrue(map)) {
//						return;
//					}
//				}
//				for (String action : actions) {
//					if (action.equals("should be given to bus")) {
//						bus.emit(getAction(action));
//					} else if (action.equals("should be given to each sprite")) {
//						for (Sprite sprite : model.getSprites()) {
//							sprite.emit(getAction(action));
//						}
//					} else if (action.equals("specific sprite action")) {
//						model.getByID(new SpriteID("spriteID")).get().emit(getAction(action));
//					}
//				}
//			}
//		};
//	}

}
