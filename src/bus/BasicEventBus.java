package bus;

/**
 * A Basic implementation of the {@link EventBus}
 * @author keping
 *
 */
public class BasicEventBus implements EventBus {

	private final SubscriberRegistry subscribers = new SubscriberRegistry();
	
	@Override
	public <T extends BusEvent> void on(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler) {
		subscribers.register(eventType, eventHandler);
	}
	
	@Override
	public <T extends BusEvent> void off(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler) {
		subscribers.unregister(eventType, eventHandler);
	}
	
	@Override
	public void emit(BusEvent busEvent) {
		for (Subscriber<? extends BusEvent> subscriber : subscribers) {
			subscriber.handle(busEvent);
		}
	}
	
}
