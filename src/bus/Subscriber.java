package bus;

final class Subscriber<T extends BusEvent> {

	private BusEventType<T> busEventType;
	private BusEventHandler<? super T> busEventHandler;
	
	Subscriber(BasicEventBus bus, BusEventType<T> busEventType, BusEventHandler<? super T> busEventHandler) {
		this.busEventType = busEventType;
		this.busEventHandler = busEventHandler;
	}
	
	BusEventType<T> getBusEventType() {
		return busEventType;
	}
	
	BusEventHandler<? super T> getBusEventHanlder() {
		return busEventHandler;
	}
	
	@SuppressWarnings("unchecked")
	void handle(BusEvent busEvent) {
		if (busEventType == busEvent.getEventType()) {
			try { // TODO: a better way to check whether busEvent could be cast to T
				busEventHandler.handle((T) busEvent);
			} catch(ClassCastException e) {
				// don't handle if the specified eventType and the constructed eventType differ.
			};
		}
	}
	
}
