package bus;

/**
 * An EventBus for registering and emitting events.
 * @author keping
 *
 */
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
	
	
	/**
	 * Register an eventHandler for a certain type of event.
	 * @param eventType
	 * @param eventHandler
	 */
	public <T extends BusEvent> void on(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler);
	
	/**
	 * Unregister an eventHandler for a certain type of event. 
	 * Do nothing if the handler is not previously registered.
	 * 
	 * Note that both eventType and eventHanlder must be the 
	 * same instances as registered.
	 * @param eventType
	 * @param eventHandler
	 */
	public <T extends BusEvent> void off(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler);
	
	/**
	 * Emits a BusEvent. It will be handled by all registered eventHandlers. 
	 * 
	 * For sub types, note that the event is handled if all following conditions hold:
	 * 1) The specified eventType during creation is a subtype of the registered eventType.
	 * 2) The created event is subtype of (can be cast to) the registered event type T.
	 * @param event
	 */
	public void emit(BusEvent event);
	
}
