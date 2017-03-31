package eventbus;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public interface IEventBus {
	/**
	 * Register event handler to a certain event type. After on(...) and before
	 * off(...), when an event of type eventType is emitted, the the handle
	 * method within eventHandler will get executed with the event passed in.
	 * 
	 * @param eventType
	 * @param eventHandler
	 */
	<T extends Event> void on(EventType<T> eventType, EventHandler<? super T> eventHandler);

	/**
	 * Remove the eventHandler from the event bus. Note this eventHanlder must
	 * be the same instance as the one used with on(eventType, eventHandler).
	 * 
	 * @param eventType
	 * @param eventHandler
	 */
	<T extends Event> void off(EventType<T> eventType, EventHandler<? super T> eventHandler);

	/**
	 * Emit (fire) an event. All events emitted must be cloneable: they are
	 * always cloneable but the default clone() behavior is shallow copying. To
	 * have a deep copy, you have to override the clone() method in your event
	 * class.
	 * 
	 * @param event
	 */
	public void emit(Event event);

}