package engine.spritecreation.factory;

import java.lang.reflect.Constructor;

import data.AttributeData;
import engine.sprite.healthholder.HealthHolder;
import engine.spritecreation.SpriteCreationException;

public class HealthHolderAttributeFactory implements AttributeFactory{
	public static final String HEALTH_VAR = "health";
	public static final String BASE_PATH = "engine.sprite.";
	
	@Override
	public HealthHolder createAttribute(AttributeData data) {
		try{
			Class <?> clazz = Class.forName(BASE_PATH + data.getName().toLowerCase() + "." + data.getImplementation());
			Constructor ctor = clazz.getConstructor(double.class);
			return (HealthHolder) ctor.newInstance(Integer.parseInt(data.getVariable(HEALTH_VAR)));
		}
		catch(Exception e){
			throw new SpriteCreationException("HealthHolder object not found " + data.getImplementation());
		}
	}
	
}
