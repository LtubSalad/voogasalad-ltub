package examples;

import api.Sprite.Sprite;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Point2D;

/**
 * Needed for example code. This is a certain kind of event used by the event
 * bus which is fired on a click
 */
public class ClickTargetEvent extends Event{
	
	private Sprite sprite;
	
	public static final EventType<ClickTargetEvent> ANY = new EventType<>(Event.ANY, "CLICK_TARGET_EVENT");
	
	public ClickTargetEvent(EventType<? extends Event> eventType, Sprite sprite) {
		super(eventType);
		this.sprite = sprite;
	}
	
	public Sprite getSprite(){
		return sprite;
	}

	public Point2D getLocation() {
		return null;
	}
}
