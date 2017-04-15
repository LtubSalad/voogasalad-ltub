package newengine.sprite;

import newengine.attribute.Attribute;
import newengine.attribute.AttributeType;

public class PositionTestAttribute extends Attribute {
	
	public static final AttributeType<PositionTestAttribute> TYPE = new AttributeType<>();

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Attribute> AttributeType<T> getType() {
		return (AttributeType<T>) TYPE;
	}

	
	public double getPositionX() {
		return 10;
	}
	
}
