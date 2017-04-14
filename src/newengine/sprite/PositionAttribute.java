package newengine.sprite;

import engine.camera.GamePoint;

public class PositionAttribute extends Attribute {
	
	public static final AttributeType<PositionTestAttribute> TYPE = new AttributeType<>();

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Attribute> AttributeType<T> getType() {
		return (AttributeType<T>) TYPE;
	}

	
	private GamePoint pos;

	public GamePoint getPos() {
		return pos;
	}
	public void setPos(GamePoint pos) {
		this.pos = pos;
	}


}
