package engine.spritecreation.factory;

import data.AttributeData;
import engine.sprite.movable.Movable;

public class MovableAttributeFactory implements AttributeFactory{
	public static final String SPEED_VAR = "speed";
	@Override
	public Movable createAttribute(AttributeData data) {
		double speed = Double.parseDouble(data.getVariable(SPEED_VAR));
		return new Movable(5.0);
	}

}
