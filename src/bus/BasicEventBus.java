package bus;

import java.util.ArrayList;
import java.util.List;

/**
 * A Basic implementation of the {@link EventBus}
 * @author keping
 *
 */
public class BasicEventBus implements EventBus {
	class Pair<T extends BusEvent> {
		private BusEventType<T> eventType;
		private BusEventHandler<? super T> eventHandler;
		Pair(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler) {
			this.eventType = eventType;
			this.eventHandler = eventHandler;
		}
		BusEventType<T> getBusEventType() { return eventType; }
		BusEventHandler<? super T> getBusEventHandler() { return eventHandler; }
	}
	
	private final SubscriberRegistry subscribers = new SubscriberRegistry();
	List<Pair<BusEvent>> handlersToAdd = new ArrayList<>();
	List<Pair<BusEvent>> handlersToRemove = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends BusEvent> void on(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler) {
		handlersToAdd.add((Pair<BusEvent>) new Pair<T>(eventType, eventHandler));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends BusEvent> void off(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler) {
		handlersToRemove.add((Pair<BusEvent>) new Pair<T>(eventType, eventHandler));
	}
	
	@Override
	public void emit(BusEvent busEvent) {
		for (Pair<BusEvent> pair : handlersToAdd) {
			subscribers.register(pair.getBusEventType(), pair.getBusEventHandler());
		} // TODO: the order of add and remove. things to add & remove should be storesd in the same queue
		handlersToAdd.clear();
		for (Pair<BusEvent> pair : handlersToRemove) {
			subscribers.unregister(pair.getBusEventType(), pair.getBusEventHandler());
		}
		handlersToRemove.clear();
		for (Subscriber<? extends BusEvent> subscriber : subscribers) {
			subscriber.handle(busEvent);
		}
	}
	
}
