package engine.skill;

import commons.Point;
import engine.sprite.Sprite;

public class Target {
	
	private Sprite sprite = null;
	private Point location = null;
	
	public Target(Sprite sprite) {
		this.sprite = sprite;
	}
	public Target(Point location) {
		this.location = location;
	}
	
	public boolean isSprite() {
		return sprite != null;
	}
	public boolean isLocation() {
		return location != null;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public Point getLocation() {
		return location;
	}
}
