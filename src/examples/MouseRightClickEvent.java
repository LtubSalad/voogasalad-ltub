package examples;

import api.Sprite.Sprite;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Point2D;

public class MouseRightClickEvent extends Event{

	private Sprite sprite;
	private static final long serialVersionUID = 1L;
	public static final EventType<MouseRightClickEvent> ANY = new EventType<>(Event.ANY, "MOUSE_RIGHT_CLICK_EVENT");
	
	public MouseRightClickEvent(EventType<? extends Event> eventType, Sprite sprite) {
		super(eventType);
		this.sprite = sprite;
	}

	public Sprite getSprite(){
		return sprite;
	}

	public Point2D getLocation() {
		return sprite.getLocation();
	}
}
