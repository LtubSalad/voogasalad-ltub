package newengine.utils;


import java.util.Optional;

import commons.point.GamePoint;
import newengine.sprite.Sprite;
import newengine.sprite.components.Position;

public class Target {
	
	private GamePoint location = null;
	private Sprite sprite = null;
	
	public Target(Sprite sprite) {
		this.sprite = sprite;
		this.location = sprite.getComponent(Position.TYPE).get().pos();
	}
	public Target(GamePoint location) {
		this.location = location;
	}
	public Target(GamePoint location, Sprite sprite) {
		this.location = location;
		this.sprite = sprite;
	}
	
	public Optional<Sprite> getSprite() {
		return Optional.ofNullable(sprite);
	}
	public GamePoint getLocation() {
		return location;
	}
}
