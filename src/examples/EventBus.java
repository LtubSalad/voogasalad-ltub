package examples;

import api.EventBus.IEventBus;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public class EventBus implements IEventBus {
	String name; 
	public EventBus(){
		name = "tester";
	}
	public void emit(SpriteAttackedEvent ev){
		; 
	}
	@Override
	public <T extends Event> void on(EventType<T> et, EventHandler<? super T> eh) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public <T extends Event> void off(EventType<T> et, EventHandler<? super T> eh) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void emit(Event ev) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public EventBus getBus() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
