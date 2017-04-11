package engine.sprite.movable;

import commons.MathUtils;
import commons.RunningMode;
import data.AttributeData;
import engine.camera.GamePoint;
import engine.skill.Target;
import engine.sprite.Attribute;
import engine.sprite.Sprite;

public class Movable implements Attribute {
	
	private Sprite sprite;
	private double speed;
	private GamePoint pDest;
	private Boolean isMovable;

	public Movable(AttributeData data){
		this.speed = Double.parseDouble(data.getVariable("speed"));	
	}
	
	public Movable(Sprite sprite) {
		this.sprite = sprite;
		isMovable = false;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void moveTo(GamePoint pDest) {
		this.pDest = pDest;
		switchOn();
	}
	public void moveTo(Target target) {
		// TODO
	}

	public double update(double dt) {
		if (RunningMode.DEV_MODE) {
			if (sprite == null) {
				System.out.println("Sprite is not set for movable!");
			}
		}
		if (!isMovable) {
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
			switchOff();
			return dt;
		}
		double xDiff = xDest - x;
		double yDiff = yDest - y;
		double dist = pos.distFrom(pDest);

		if (speed * dt > dist) {
			// arrives at destination at this frame.
			sprite.setPos(new GamePoint(xDest, yDest));
			switchOff();
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

	public void switchOn() {
		isMovable = true;
	}

	public void switchOff() {
		isMovable = false;
	}


}
