package newengine.sprite.components;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.events.sprite.ChangeSpeedEvent;
import newengine.events.sprite.MoveEvent;
import newengine.events.sprite.StateChangeEvent;
import newengine.events.sprite.UpgradeEvent;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Speed extends Component {
	
	public static final ComponentType<Speed> TYPE = new ComponentType<>(Speed.class.getName());
	
	public static final double MIN_SPEED = 0;
	public static final double MAX_SPEED = 100;
	private double speed;
	private double acceleratedSpeed;
	
	@ConstructorForDeveloper
	public Speed(@VariableName(name = "speed") double speed, double acceleratedSpeed) {
		this.speed = speed;
		this.acceleratedSpeed = acceleratedSpeed;
	}
	
	@Override
	public void afterAdded() { 
		sprite.on(UpgradeEvent.DOUBLE, e -> {
			speed = speed*2;
			sprite.emit(new StateChangeEvent(StateChangeEvent.SPEED, sprite, speed));
		});
		sprite.on(ChangeSpeedEvent.SPEED, (e) -> {
			speed += acceleratedSpeed * e.dt();
			if (speed > MAX_SPEED) {
				speed = MAX_SPEED;
			}
			else if (speed < MIN_SPEED) {
				speed = MIN_SPEED;
			}
			if (speed > 0) {
				sprite.emit(new MoveEvent(MoveEvent.START_AUTO));
			}
			else if (speed < 0) {
				sprite.emit(new MoveEvent(MoveEvent.STOP));
			}
		});
	}
	
	public double speed() {
		return speed;
	}	
	
	public double acceleratedSpeed() {
		return acceleratedSpeed;
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Speed clone() {
		return new Speed(speed, acceleratedSpeed);
	}

	@Override
	public Object[] getGUIParameters() {
		Object[] parameters=new Object[1];
		parameters[0]=speed;
		parameters[1]=acceleratedSpeed;
		return parameters;
	}
}
