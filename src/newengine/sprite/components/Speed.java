package newengine.sprite.components;

import commons.point.GamePoint;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.events.collision.CollisionEvent;
import newengine.events.sound.EngineSoundEvent;
import newengine.events.sprite.ChangeSpeedEvent;
import newengine.events.sprite.MoveEvent;
import newengine.events.sprite.StateChangeEvent;
import newengine.events.sprite.UpgradeEvent;
import newengine.sprite.Sprite;
import newengine.sprite.SpriteID;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Speed extends Component {
	
	public static final ComponentType<Speed> TYPE = new ComponentType<>(Speed.class.getName());
	
	public static final double MIN_SPEED = -200;
	public static final double MAX_SPEED = 1000;
	private double speed;
	private double acceleration;
	
	@ConstructorForDeveloper
	public Speed(@VariableName(name = "speed") double speed, double acceleratedSpeed) {
		this.speed = speed;
		this.acceleration = acceleratedSpeed;
	}
	
	@Override
	public void afterAdded() { 
		sprite.on(UpgradeEvent.DOUBLE, e -> {
			speed = speed*2;
			sprite.emit(new StateChangeEvent(StateChangeEvent.SPEED, sprite, speed));
		});
		sprite.on(ChangeSpeedEvent.SPEED, (e) -> {
			if (speed > 500 && e.dt() < 0) {
				speed += acceleration * 4 * e.dt();
			} else if (speed > 0 && e.dt() < 0) {
				speed += acceleration * 2.5 * e.dt();
			} else {
				speed += acceleration * e.dt();
			}
			if (speed > MAX_SPEED) {
				speed = MAX_SPEED;
			}
			else if (speed < MIN_SPEED) {
				speed = MIN_SPEED;
			}
			if (speed > 0) {
				sprite.emit(new MoveEvent(MoveEvent.START_AUTO));
			}
			sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(
					new EngineSoundEvent(EngineSoundEvent.UPDATE_ENGINE, speed, acceleration));
		});
		sprite.on(CollisionEvent.ANY, (e) -> {
			if (sprite.getID().equals(SpriteID.CAR_ID)) {
				Sprite tree = e.getSprite2();
				GamePoint pos1 = sprite.getComponent(Position.TYPE).get().pos();
				GamePoint pos2 = tree.getComponent(Position.TYPE).get().pos();
				speed = 0;
			}
		});
	}
	
	public double speed() {
		return speed;
	}	
	
	public double acceleratedSpeed() {
		return acceleration;
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Speed clone() {
		return new Speed(speed, acceleration);
	}

	@Override
	public Object[] getGUIParameters() {
		Object[] parameters=new Object[1];
		parameters[0]=speed;
		parameters[1]=acceleration;
		return parameters;
	}
}
