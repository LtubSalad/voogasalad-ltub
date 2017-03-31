package examples;

import api.Sprite.Sprite;
import javafx.event.Event;
import javafx.event.EventType;

public class MouseRightClickEvent extends Event{

	public static final EventType<MouseRightClickEvent> ANY = new EventType<>(Event.ANY, "MOUSE_RIGHT_CLICK_EVENT");
	
	public MouseRightClickEvent(EventType<? extends Event> eventType) {
		super(eventType);
		// TODO Auto-generated constructor stub
	}

	public Sprite getSprite() {
		return null;
	}

	public Sprite getLocation() {
		// TODO Auto-generated method stub
		return null;
	}
}
