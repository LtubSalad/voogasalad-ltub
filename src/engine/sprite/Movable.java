package engine.sprite;

import commons.MathUtils;
import commons.RunningMode;

public class Movable {
	
	Sprite sprite;
	private double speed = 100; // TODO: game data
	private boolean moving = false;
	private double xDest;
	private double yDest;
	
	public Movable(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void moveTo(double xDest, double yDest) {
		this.xDest = xDest;
		this.yDest = yDest;
		startMoving();
	}
	
	private void startMoving() {
		moving = true;
	}
	private void stopMoving() {
		moving = false;
	}
	public boolean isMoving() {
		return moving;
	}

	public void update(double dt) {
		if (RunningMode.DEV_MODE) {
			if (sprite == null) {
				System.out.println("Sprite is not set for movable!");
			}
		}
		if (!isMoving()) {
			return; 
		}
		if (sprite != null) {
			double x = sprite.x();
			double y = sprite.y();
			if (MathUtils.doubleEquals(x, xDest) && 
					MathUtils.doubleEquals(y, yDest)) {
				stopMoving();
				return;
			}
			double xDiff = xDest - x;
			double yDiff = yDest - y;
			double dist = Math.sqrt(xDiff*xDiff+yDiff*yDiff);
			
			if (speed * dt >= dist) {
				// arrives at destination at this frame.
				sprite.setX(xDest);
				sprite.setY(yDest);
				stopMoving();
				return;
			}
			
			// not arrived at destination, move by time and speed.
			double vx = 0;
			double vy = 0;
			if (!MathUtils.doubleEquals(x, xDest) &&
					MathUtils.doubleEquals(y, yDest)) {
				vx = xDiff > 0 ? speed : -speed;
			} else if (MathUtils.doubleEquals(x, xDest) &&
					!MathUtils.doubleEquals(y, yDest)) {
				vy = yDiff > 0 ? speed : -speed;
			} else {
				vx = speed / dist * xDiff;
				vy = speed / dist * yDiff;
			}
			sprite.setX(x + vx*dt);
			sprite.setY(y + vy*dt);
		}
	}
	

}
