package newengine.sprite.components;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import bus.BusEvent;
import commons.MathUtils;
import commons.point.GamePoint;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.DeveloperMethod;
import helperAnnotations.VariableName;
import newengine.events.QueueEvent;
import newengine.events.SpriteModelEvent;
import newengine.events.collision.CollisionEvent;
import newengine.events.sound.EngineSoundEvent;
import newengine.events.sound.SoundEvent;
import newengine.events.sprite.ChangeSpeedEvent;
import newengine.events.sprite.MoveEvent;
import newengine.events.sprite.StateChangeEvent;
import newengine.events.stats.ChangeLivesEvent;
import newengine.sprite.Sprite;
import newengine.sprite.SpriteID;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;
import newengine.utils.checker.CollisionChecker;

public class Position extends Component {
	public static final ComponentType<Position> TYPE = new ComponentType<>(Position.class.getName());
	private GamePoint pos = new GamePoint();
	private double heading;
	private static final double TURN_AROUND_SPEED = 80;
	@XStreamOmitField
	private Target target;
	private boolean isMoving = false;
	private boolean followingSprite = false;

	public Position(GamePoint pos, double heading) {
		this.pos = pos;
		this.heading = heading;
	}
	
	public Position(GamePoint pos){
		this(pos, 0);
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
			stopMoving();
			if (e.getSprite().getComponent(Weapon.TYPE).isPresent()) {
				sprite.getComponent(GameBus.TYPE).get().getGameBus()
						.emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, e.getSprite()));
			}
		});
		sprite.on(MoveEvent.START_AUTO, (e) -> {
			sprite.getComponent(GameBus.TYPE).ifPresent((gameBus) -> {
				gameBus.getGameBus().emit(new EngineSoundEvent(EngineSoundEvent.START_ENGINE, -1, -1));
			});
			startMoving();
		});
		sprite.on(ChangeSpeedEvent.DIRECTION, (e) -> {
			changeHeading(e.dt());
		});
		sprite.on(CollisionEvent.ANY, (e) -> {
			if (sprite.getID().equals(SpriteID.CAR_ID)) {
				Sprite tree = e.getSprite2();
				GamePoint posCar = pos;
				GamePoint posTree = tree.getComponent(Position.TYPE).get().pos();
				double dist = posCar.distFrom(posTree);
				double sepDist = CollisionChecker.findSepExtraDist(sprite, tree);
				pos = new GamePoint(
						posTree.x() + (posCar.x() - posTree.x()) / dist * (dist + sepDist),
						posTree.y() + (posCar.y() - posTree.y()) / dist * (dist + sepDist)
						);
			}
		});
	}
	

	@Override
	public void onUpdated(double dt) {
		sprite.getComponent(PathFollower.TYPE).ifPresent((pathFollower) -> {
			if (sprite.getComponent(EventQueue.TYPE).get().isEmpty()) {
				sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(new ChangeLivesEvent(ChangeLivesEvent.CHANGE,
						sprite.getComponent(Owner.TYPE).get().player(), -1));
				sprite.getComponent(GameBus.TYPE).get().getGameBus()
						.emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, sprite));
			}
		});
//		if (!isMoving()) {
//			return;
//		}
		GamePoint pDest = getFollowingPoint();
		if (pDest != null) {
			updatePosition(dt, pDest);
		}
		else {
			updateTime(dt);
		}
	}

	private void updatePosition(double dt, GamePoint pDest) {
		if (!sprite.getComponent(Speed.TYPE).isPresent()) { return;	}

		double xDest = pDest.x();
		double yDest = pDest.y();
		double x = pos.x();
		double y = pos.y();

		double xDiff = xDest - x;
		double yDiff = yDest - y;
		double dist = pos.distFrom(pDest);
		double speed = sprite.getComponent(Speed.TYPE).get().speed();

		if (speed * dt > dist) {
			// arrives at destination at this frame.
			pos = new GamePoint(xDest, yDest);
			target.getSprite().ifPresent((targetSprite) -> {
				targetSprite.emit(new MoveEvent(MoveEvent.STOP, sprite, target));
				isMoving = false;
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
		sprite.emit(new StateChangeEvent(StateChangeEvent.XPOS, sprite, pos.x()));
		sprite.emit(new StateChangeEvent(StateChangeEvent.YPOS, sprite, pos.y()));
		return;
	}
	
	private void updateTime(double dt) {
		if (!sprite.getComponent(Speed.TYPE).isPresent()) {return;}
		
		double speed = sprite.getComponent(Speed.TYPE).get().speed();
		double x = pos.x();
		double y = pos.y();
		// TODO heading
		double xSpeed = speed * Math.cos(Math.toRadians(heading));
		double ySpeed = speed * Math.sin(Math.toRadians(heading));
		pos = new GamePoint(x + dt * xSpeed, y + dt * ySpeed);
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

	public double xPos() {
		return pos.x();
	}

	public double yPos() {
		return pos.y();
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
	
	private void changeHeading(double dt) {
		heading += TURN_AROUND_SPEED * dt;
	}

	public boolean isMoving() {
		return isMoving;
	}

	private void followingSprite() {
		followingSprite = true;
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
		if (target != null) {
			return target.getLocation();
		}
		return null;
	}

	@Override
	public Object[] getGUIParameters() {
		Object[] parameters=new Object[3];
		parameters[0]=pos.x();
		parameters[1]=pos.y();
		parameters[2]=heading;
		return parameters;
	}
}
