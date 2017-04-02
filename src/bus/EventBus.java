package bus;


public interface EventBus {
	/* Implementation Note:
	 * 1) Here the methods are generic, <T extends Event>.
	 * Note that here the generic for EventHandler is <? super T>.
	 * Because if an eventHandler can handle a parent event of T,
	 * then this eventHandler can also handle T.
	 * 2) The event type has to be specified explicitly, so that
	 * the compiler knows what BusEventHandler could handle even
	 * when using lambda expressions. 
	 * 3) The emit method only takes BusEvent, the event type checking 
	 * is done inside the implementation.
	 */
	
	
	
	public <T extends BusEvent> void on(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler);
	
	public <T extends BusEvent> void off(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler);
	
	public void emit(BusEvent event);
	
}
