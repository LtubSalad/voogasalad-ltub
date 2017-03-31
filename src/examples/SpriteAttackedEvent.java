package examples;

import api.Sprite.Sprite;
import javafx.event.Event;
import javafx.event.EventType;

public class SpriteAttackedEvent extends Event{
	
	private Sprite mySprite; 
	public static final EventType<SpriteAttackedEvent> ANY = new EventType<>(Event.ANY, "SPRITE_ATTACKED_EVENT");
	
	public SpriteAttackedEvent(EventType<? extends Event> eventType, Sprite sprite) {
		super(eventType);
		this.mySprite = sprite;
	}

	
	
}
