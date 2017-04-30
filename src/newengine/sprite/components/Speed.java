package newengine.sprite.components;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.events.sprite.StateChangeEvent;
import newengine.events.sprite.UpgradeEvent;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Speed extends Component {
	
	public static final ComponentType<Speed> TYPE = new ComponentType<>(Speed.class.getName());
	
	private double speed;
	
//	public Speed(double speed) {
//		this.speed = speed;
//	}
	
	@ConstructorForDeveloper
	public Speed(@VariableName(name = "speed") double speed) {
		this.speed = speed;
	}
	
	@Override
	public void afterAdded() { 
		sprite.on(UpgradeEvent.DOUBLE, e -> {
			speed = speed*2;
			sprite.emit(new StateChangeEvent(StateChangeEvent.SPEED, sprite, speed));
		});
	}
	
	public double speed() {
		return speed;
	}	
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Speed clone() {
		return new Speed(speed);
	}

	@Override
	public Object[] getParameters() {
		Object[] parameters=new Object[1];
		parameters[0]=speed;
		return parameters;
	}
}
