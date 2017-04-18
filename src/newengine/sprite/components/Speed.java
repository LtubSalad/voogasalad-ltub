package newengine.sprite.components;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.variable.Var;

public class Speed extends Component {
	
	public static final ComponentType<Speed> TYPE = new ComponentType<>(Speed.class.getName());
	
	private final Var<Double> speedVar = new Var<>();
	
	public Speed(double speed) {
		speedVar.set(speed);
	}
	
	@Override
	public void afterAdded() { }
	
	public double speed() {
		return speedVar.get();
	}	

	@Override
	public void onUpdated(double dt) {
//		System.out.println("There is no change to SpeedComponent.");
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

}
