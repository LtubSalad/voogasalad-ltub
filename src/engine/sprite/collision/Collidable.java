package engine.sprite.collision;

public class Collidable {

	private CollisionBound bound;
	private boolean isCollidable = true;
	
	public Collidable(CollisionBound bound) {
		this.bound = bound;
	}
	
	public void switchOn() {
		isCollidable = true;
	}
	public void switchOff() {
		isCollidable = false;
	}
	public boolean isCollidable() {
		return isCollidable;
	}
	public CollisionBound getCollisionBound() {
		return bound;
	}
	
}
