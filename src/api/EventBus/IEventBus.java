package api.EventBus;

import examples.EventBus;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.event.EventHandler;

public interface IEventBus {

	public <T extends Event> void on(EventType<T> et, EventHandler<? super T> eh);
	
	public <T extends Event> void off(EventType<T> et, EventHandler<? super T> eh);
	
	public void emit(Event ev);

	public EventBus getBus();
	
}
