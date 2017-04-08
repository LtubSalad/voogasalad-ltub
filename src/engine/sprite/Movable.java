package engine.sprite;

import commons.MathUtils;
import commons.RunningMode;
import engine.camera.GamePoint;

public class Movable {

	Sprite sprite;
	private double speed = 300; // TODO: game data
	private boolean moving = false;
	private GamePoint pDest;

	public Movable(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void moveTo(GamePoint pDest) {
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

	public double update(double dt) {
		if (RunningMode.DEV_MODE) {
			if (sprite == null) {
				System.out.println("Sprite is not set for movable!");
			}
		}
		if (!isMoving()) {
			return dt;
		}
		if (sprite == null) {
			return dt;
		}

		double xDest = pDest.x();
		double yDest = pDest.y();
		GamePoint pos = sprite.getPos();
		double x = pos.x();
		double y = pos.y();
		if (MathUtils.doubleEquals(x, xDest) && MathUtils.doubleEquals(y, yDest)) {
			stopMoving();
			return dt;
		}
		double xDiff = xDest - x;
		double yDiff = yDest - y;
		double dist = pos.distFrom(pDest);

		if (speed * dt > dist) {
			// arrives at destination at this frame.
			sprite.setPos(new GamePoint(xDest, yDest));
			stopMoving();
			return dist - (speed * dt);
		}

		// not arrived at destination, move by time and speed.
		double vx = 0;
		double vy = 0;
		if (!MathUtils.doubleEquals(x, xDest) && MathUtils.doubleEquals(y, yDest)) {
			vx = xDiff > 0 ? speed : -speed;
		} else if (MathUtils.doubleEquals(x, xDest) && !MathUtils.doubleEquals(y, yDest)) {
			vy = yDiff > 0 ? speed : -speed;
		} else {
			vx = speed / dist * xDiff;
			vy = speed / dist * yDiff;
		}
		sprite.setPos(new GamePoint(x + vx * dt, y + vy * dt));
		return 0.0;

	}

}
