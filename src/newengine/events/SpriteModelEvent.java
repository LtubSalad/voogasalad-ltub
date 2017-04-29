package newengine.events;

import java.util.ArrayList;
import java.util.List;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.Sprite;

public class SpriteModelEvent extends BusEvent {

	public static final BusEventType<SpriteModelEvent> ADD = new BusEventType<>(
			SpriteModelEvent.class.getName() + "ADD");
	public static final BusEventType<SpriteModelEvent> REMOVE = new BusEventType<>(
			SpriteModelEvent.class.getName() + "REMOVE");

	private List<Sprite> sprites = new ArrayList<Sprite>();

	public SpriteModelEvent(BusEventType<? extends BusEvent> busEventType, List<Sprite> sprites) {
		super(busEventType);
		this.sprites = sprites;
	}
	
	public SpriteModelEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite){
		super(busEventType);
		sprites.add(sprite);
	}

	public List<Sprite> getSprites() {
		return sprites;
	}

}
