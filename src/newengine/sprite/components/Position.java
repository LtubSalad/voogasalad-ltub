package newengine.sprite.components;

import bus.BusEvent;
import commons.MathUtils;
import commons.point.GamePoint;
import newengine.events.QueueEvent;
import newengine.events.sound.SoundEvent;
import newengine.events.sprite.MoveEvent;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class Position extends Component {

	public static final ComponentType<Position> TYPE = new ComponentType<>(Position.class.getName());
	private GamePoint pos;
	private double heading;
	private Target target;
	private boolean isMoving = false;
	private boolean followingSprite = false;
	
	public Position(GamePoint pos, double heading) {
		this.pos = pos;
		this.heading = heading;
	}
	
	@Override
	public void afterAdded() {
		sprite.on(MoveEvent.POSITION, (e) -> {
			moveTo(e.getTarget());
			sprite.getComponent(SoundEffect.TYPE).ifPresent((sound) -> {
				sprite.getComponent(GameBus.TYPE).ifPresent((bus) -> {
					bus.getGameBus().emit(new SoundEvent(SoundEvent.SOUND_EFFECT, sound.getMoveSoundFile()));
				});
			});
		});
		sprite.on(MoveEvent.SPRITE, (e) -> {
			moveTo(e.getTarget());
			followingSprite();
			sprite.getComponent(SoundEffect.TYPE).ifPresent((sound) -> {
				sprite.getComponent(GameBus.TYPE).ifPresent((bus) -> {
					bus.getGameBus().emit(new SoundEvent(SoundEvent.SOUND_EFFECT, sound.getMoveSoundFile()));
				});
			});
		});
	}

	@Override
	public void onUpdated(double dt) {
		if (!isMoving()) {return;}
		GamePoint pDest = getFollowingPoint();
		updateMovePosition(dt, pDest);
	}

	private void updateMovePosition(double dt, GamePoint pDest) {
		double xDest = pDest.x();
		double yDest = pDest.y();
		double x = pos.x();
		double y = pos.y();
		if (MathUtils.doubleEquals(x, xDest) && MathUtils.doubleEquals(y, yDest)) {
			stopMoving();
		}
		double xDiff = xDest - x;
		double yDiff = yDest - y;
		double dist = pos.distFrom(pDest);
		double speed = sprite.getComponent(Speed.TYPE).get().speed();
		if (speed * dt > dist) {
			// arrives at destination at this frame.
			pos = new GamePoint(xDest, yDest);
			stopMoving();
			sprite.emit(new QueueEvent(QueueEvent.NEXT, new BusEvent(BusEvent.ANY)));
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
		pos = new GamePoint(x + vx * dt, y + vy * dt);
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}
	
	@Override
	public Position clone() {
		return new Position(pos, heading);
	}

	public GamePoint pos() {
		return pos;
	}

	public double heading() {
		return heading;
	}

	private void moveTo(Target target) {
		this.target = target;
		startMoving();
	}
	
	private void startMoving() {
		isMoving = true;
	}
	
	private void stopMoving() {
		isMoving = false;
	}
	
	public boolean isMoving() {
		return isMoving;
	}
	
	private void followingSprite() {
		followingSprite = true;
	}
	
	private void followingPosition() {
		followingSprite = false;
	}
	
	private boolean isFollowingSprite() {
		return followingSprite;
	}
	
	private GamePoint getFollowingPoint() {
		if (isFollowingSprite()) {
			if (target.getSprite().isPresent()) {
				Sprite followedSprite = target.getSprite().get();
				if (followedSprite.getComponent(Position.TYPE).isPresent()) {
					return followedSprite.getComponent(Position.TYPE).get().pos();
				}
			}
		}
		return target.getLocation();
	}
}
