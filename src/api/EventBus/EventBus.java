package api.EventBus;

import javafx.event.Event;
import javafx.event.EventHandler;

public interface EventBus {

	public void on(EventType et, EventHandler eh);
	
	public void off(EventType et, EventHandler eh);
	
	public void emit(Event ev);
	
}
