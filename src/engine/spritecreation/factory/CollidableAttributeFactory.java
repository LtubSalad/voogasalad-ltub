package engine.spritecreation.factory;

import java.lang.reflect.Constructor;

import data.AttributeData;
import engine.sprite.collidable.Collidable;
import engine.sprite.movable.Movable;
import engine.spritecreation.SpriteCreationException;

public class CollidableAttributeFactory implements AttributeFactory{
	public static final String IMAGE_VAR = "collisionBoundsImage";
	public static final String BASE_PATH = "engine.sprite.";
	
	@Override
	public Collidable createAttribute(AttributeData data) {
		try{
			Class <?> clazz = Class.forName(BASE_PATH + data.getName().toLowerCase() + "." + data.getImplementation());
			Constructor ctor = clazz.getConstructor(String.class);
			return (Collidable) ctor.newInstance(data.getVariable(IMAGE_VAR));
		}
		catch(Exception e){
			throw new SpriteCreationException("Collidable object not found " + data.getImplementation());
		}
	}

}
