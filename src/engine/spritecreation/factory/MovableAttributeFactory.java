package engine.spritecreation.factory;

import java.lang.reflect.Constructor;

import data.AttributeData;
import engine.sprite.movable.Movable;
import engine.spritecreation.SpriteCreationException;

/**
 * AttributeFactory which creates a MovableAttribute 
 * @author Matthew Tribby
 */
public class MovableAttributeFactory implements AttributeFactory{
	public static final String SPEED_VAR = "speed";
	public static final String BASE_PATH = "engine.sprite.";
		
	/**
	 * Creates Movable Attribute based on data
	 * @param AttributeData
	 * @return Movable Attribute based on data
	 */
	@Override
	public Movable createAttribute(AttributeData data) { 
		try{
			Class <?> clazz = Class.forName(BASE_PATH + data.getName().toLowerCase() + "." + data.getImplementation());
			Constructor ctor = clazz.getConstructor(double.class);
			return (Movable) ctor.newInstance(Double.parseDouble(data.getVariable(SPEED_VAR)));
		}
		catch(Exception e){
			throw new SpriteCreationException("Movable object not found " + data.getImplementation());
		}
	}

}
