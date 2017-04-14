package engine.spritecreation.factory;

import java.lang.reflect.Constructor;

import data.AttributeData;
import engine.sprite.attacker.Attacker;
import engine.sprite.movable.Movable;
import engine.spritecreation.SpriteCreationException;

/**
 * Attribute factory which creates an Attacker Attribute based in an AttributeData class 
 * @author Matthew Tribby
 */
public class AttackerAttributeFactory implements AttributeFactory {
	public static final String RADIUS_VAR = "radius";
	public static final String DAMAGE_VAR = "damage";
	public static final String BASE_PATH = "engine.sprite.";

	@Override
	/**
	 * Actually creates the attribute
	 * @return configured attribute
	 */
	public Attacker createAttribute(AttributeData data) {
		try{
			Class <?> clazz = Class.forName(BASE_PATH + data.getName().toLowerCase() + "." + data.getImplementation());
			Constructor ctor = clazz.getConstructor(double.class, int.class);
			return (Attacker) ctor.newInstance(Double.parseDouble(data.getVariable(RADIUS_VAR)), Integer.parseInt(data.getVariable(DAMAGE_VAR)));
		}
		catch(Exception e){
			throw new SpriteCreationException("Attacker object not found " + data.getImplementation());
		}
	}

}
