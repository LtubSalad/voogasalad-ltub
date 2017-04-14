package newengine.sprite;

public class SpeedAttribute extends Attribute {
	
	public static final AttributeType<SpeedAttribute> TYPE = new AttributeType<>();

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Attribute> AttributeType<T> getType() {
		return (AttributeType<T>) TYPE;
	}

	private double speed;
	private double heading;
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public void setHeading(double heading) {
		this.heading = heading;
	}
	public double getSpeed() {
		return speed;
	}	
	public double getHeading() {
		return heading;
	}
}
