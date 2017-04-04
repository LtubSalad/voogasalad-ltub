package engine.sprite;

import commons.MathUtils;
import commons.Point;
import commons.RunningMode;

public class Movable {
	
	Sprite sprite;
	private double speed = 300; // TODO: game data
	private boolean moving = false;
	private Point pDest;
	
	public Movable(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void moveTo(Point pDest) {
		this.pDest = pDest;
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
			double xDest = pDest.x();
			double yDest = pDest.y();
			Point pos = sprite.getPos();
			double x = pos.x();
			double y = pos.y();
			if (MathUtils.doubleEquals(x, xDest) && 
					MathUtils.doubleEquals(y, yDest)) {
				stopMoving();
				return;
			}
			double xDiff = xDest - x;
			double yDiff = yDest - y;
			double dist = pos.distFrom(pDest);
			
			if (speed * dt >= dist) {
				// arrives at destination at this frame.
				sprite.setPos(new Point(xDest, yDest));
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
			sprite.setPos(new Point(x+vx*dt, y+vy*dt));
		}
	}
	

}
