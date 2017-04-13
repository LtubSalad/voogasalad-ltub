package engine.spritecreation.factory;

import data.AttributeData;
import engine.sprite.Attribute;

/**
 * This factory is responsible for creating the AI attribute based on AttributeData passed in
 * @author Matthew Tribby
 * TODO finish now that we have the actual AI attribute made
 */
public class AIAttributeFactory implements AttributeFactory{
	public static final String AI_TYPE_VAR = "aiType";
	public static final String PATH_VAR = "path";
	
	/**
	 * Actually creates the attribute
	 */
	@Override
	public Attribute createAttribute(AttributeData data) {
	//	return new 
		return null;
	}

}
