package bus;

/**
 * Used to store the registered eventType and the corresponding handler. 
 * @author keping
 *
 * @param <T>
 */
final class Subscriber<T extends BusEvent> {

	private BusEventType<T> registeredEventType;
	private BusEventHandler<? super T> registeredHandler;
	
	Subscriber(BusEventType<T> busEventType, BusEventHandler<? super T> busEventHandler) {
		this.registeredEventType = busEventType;
		this.registeredHandler = busEventHandler;
	}
	
	BusEventType<T> getBusEventType() {
		return registeredEventType;
	}
	
	BusEventHandler<? super T> getBusEventHanlder() {
		return registeredHandler;
	}
	
	/**
	 * The event is handled if following conditions both hold:
	 * 1) The specified eventType during creation is a subtype of the registered eventType.
	 * 2) The created event is subtype of (can be cast to) the registered event type T.
	 * @param busEvent
	 */
	@SuppressWarnings("unchecked")
	void handle(BusEvent busEvent) {
		BusEventType<? extends BusEvent> specifiedEventType = busEvent.getEventType();
		while (!registeredEventType.equals(specifiedEventType) && 
				!specifiedEventType.equals(BusEventType.ROOT)) {
			specifiedEventType = specifiedEventType.getSuperType();
		}
		if (registeredEventType.equals(specifiedEventType)) {
			if (registeredHandler == null) {
				System.out.println("registered handler is null");
			}
			try { // TODO: a better way to check whether busEvent could be cast to T
				registeredHandler.handle((T) busEvent);
			} catch(ClassCastException e) {
				// don't handle if the specified eventType and the constructed eventType differ.
			};
		}
	}
	
}
