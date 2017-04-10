package engine.sprite.collision;

import engine.sprite.Attribute;

public class Collidable implements Attribute{

	private CollisionBound bound;
	private Boolean isCollidable;
	
	public Collidable(CollisionBound bound) {
		this.bound = bound;
		isCollidable = true;
	}
	
	public CollisionBound getCollisionBound() {
		return bound;
	}

	@Override
	public void switchOn() {
		isCollidable = true;
	}

	@Override
	public void switchOff() {
		isCollidable = false;
	}

	@Override
	public Boolean isAttribute() {
		return isCollidable;
	}

	@Override
	public double update(double dt) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
