package newengine.sprite.speed;

import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SpeedComponent extends Component{
	
	public static final ComponentType<SpeedComponent> TYPE = new ComponentType<>();

	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	private Sprite sprite;
	private double speed;
	private double heading;
	
	public SpeedComponent(double speed, double heading) {
		this.speed = speed;
		this.heading = heading;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public double getHeading() {
		return heading;
	}
	
	@Override
	public void onAdded(Sprite sprite) {
		this.sprite = sprite;
		initHandlers();
	}
	
	private void initHandlers() {
		
	}
	
	@Override
	public void onUpdated(double dt) {
		System.out.println("There is no change to SpeedComponent.");
	}

}
