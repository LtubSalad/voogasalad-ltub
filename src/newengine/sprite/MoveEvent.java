package newengine.sprite;

import java.util.List;

import bus.BusEvent;
import bus.BusEventType;
import engine.skill.Target;

public class MoveEvent extends BusEvent {
	
	public static final BusEventType<MoveEvent> GAME = new BusEventType<>();
	public static final BusEventType<MoveEvent> GENERAL = new BusEventType<>();
	public static final BusEventType<MoveEvent> SPECIFIC = new BusEventType<>();
	
	private Sprite sprite;
	private List<Sprite> sprites;
	private Target target;

	public MoveEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite, Target target) {
		super(busEventType);
		this.sprite = sprite;
		this.target = target;
	}
	
	public MoveEvent(BusEventType<? extends BusEvent> busEventType, List<Sprite> sprites, Target target) {
		super(busEventType);
		this.sprites = sprites;
		this.target = target;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public List<Sprite> getSprites() {
		return sprites;
	}
	
	public Target getTarget() {
		return target;
	}

}
