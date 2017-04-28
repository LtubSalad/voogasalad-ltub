package newengine.sprite.components;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import bus.BusEvent;
import bus.BusEventHandler;
import commons.MathUtils;
import commons.point.GamePoint;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.DeveloperMethod;
import helperAnnotations.VariableName;
import newengine.events.QueueEvent;
import newengine.events.SpriteModelEvent;
import newengine.events.sound.SoundEvent;
import newengine.events.sprite.ChangeHealthEvent;
import newengine.events.sprite.FireProjectileEvent;
import newengine.events.sprite.MoveEvent;
import newengine.events.timer.DelayedEvent;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class Position extends Component {

	public static final ComponentType<Position> TYPE = new ComponentType<>(Position.class.getName());
	private GamePoint pos;
	private double heading;
	@XStreamOmitField
	private Target target;
	private boolean isMoving = false;
	private boolean followingSprite = false;

	public Position(GamePoint pos, double heading) {
		this.pos = pos;
		this.heading = heading;
	}

	@ConstructorForDeveloper
	public Position(@VariableName(name = "xPosition") double xPos, @VariableName(name = "yPosition") double yPos,
			@VariableName(name = "heading") double heading) {
		this(new GamePoint(xPos, yPos), heading);
	}

	@Override
	public void afterAdded() {
		sprite.on(MoveEvent.START_POSITION, (e) -> {
			moveTo(e.getTarget());
			sprite.getComponent(SoundEffect.TYPE).ifPresent((sound) -> {
				sprite.getComponent(GameBus.TYPE).ifPresent((bus) -> {
					if (bus.getGameBus() == null) {
						System.out.println("main bus is null");
					}
					bus.getGameBus().emit(new SoundEvent(SoundEvent.SOUND_EFFECT, sound.getMoveSoundFile()));
				});
			});
		});
		sprite.on(MoveEvent.START_SPRITE, (e) -> {
			moveTo(e.getTarget());
			followingSprite();
			sprite.getComponent(SoundEffect.TYPE).ifPresent((sound) -> {
				sprite.getComponent(GameBus.TYPE).ifPresent((bus) -> {
					bus.getGameBus().emit(new SoundEvent(SoundEvent.SOUND_EFFECT, sound.getMoveSoundFile()));
				});
			});
		});
		sprite.on(MoveEvent.STOP, (e) -> {
//			if (e.getSprite().getComponent(Weapon.TYPE).isPresent()){
//				System.out.println("the weapon reached dest so we remove it");
//				//sprite.emit(new ChangeHealthEvent(ChangeHealthEvent.ANY, sprite.getComponent(DamageStrength.TYPE).get().getStrength()));
//				sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, e.getSprite()));
//			}
			stopMoving();
		});
	}

	@Override
	public void onUpdated(double dt) {
		if (!isMoving()) {
			return;
		}
		GamePoint pDest = getFollowingPoint();
		updateMovePosition(dt, pDest);
	}

	private void updateMovePosition(double dt, GamePoint pDest) {
		double xDest = pDest.x();
		double yDest = pDest.y();
		double x = pos.x();
		double y = pos.y();
		if (MathUtils.doubleEquals(x, xDest) && MathUtils.doubleEquals(y, yDest)) {
			if (sprite.getComponent(Weapon.TYPE).isPresent()){
				System.out.println("weapon reaches target");
				sprite.emit(new MoveEvent(MoveEvent.STOP, sprite, target));
			}
			stopMoving();
			return;
		}
		double xDiff = xDest - x;
		double yDiff = yDest - y;
		double dist = pos.distFrom(pDest);
		double speed = sprite.getComponent(Speed.TYPE).get().speed();

		if (speed * dt > dist) {
			// arrives at destination at this frame.
			pos = new GamePoint(xDest, yDest);
			target.getSprite().ifPresent((targetSprite) -> {
				System.out.println("weapon reaches target");
				targetSprite.emit(new MoveEvent(MoveEvent.STOP, sprite, target));
			});
			sprite.emit(new QueueEvent(QueueEvent.NEXT, new BusEvent(BusEvent.ANY)));
			return;
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
		return;
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

	@DeveloperMethod
	private void moveTo(Target target) {
		this.target = target;
		startMoving();
	}

	@DeveloperMethod
	private void startMoving() {
		isMoving = true;
	}

	@DeveloperMethod
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
