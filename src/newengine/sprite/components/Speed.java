package newengine.sprite.components;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Speed extends Component {
	
	public static final ComponentType<Speed> TYPE = new ComponentType<>(Speed.class.getName());
	
	private final double speed;
	
//	public Speed(double speed) {
//		this.speed = speed;
//	}
	
	@ConstructorForDeveloper
	public Speed(@VariableName(name = "speed") double speed) {
		this.speed = speed;
	}
	
	@Override
	public void afterAdded() { }
	
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
