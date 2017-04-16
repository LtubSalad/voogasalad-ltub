package newengine.sprite.components;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.variable.Var;

public class Speed extends Component {
	
	public static final ComponentType<Speed> TYPE = new ComponentType<>();
	
	private final Var<Double> speedVar = new Var<>();
	private final Var<Double> headingVar = new Var<>();
	
	public Speed(double speed) {
		speedVar.set(speed);
		headingVar.set(0.0); // TODO
	}
	
	@Override
	public void afterAdded() { }
	
	public double speed() {
		return speedVar.get();
	}
	
	public double heading() {
		return headingVar.get();
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
