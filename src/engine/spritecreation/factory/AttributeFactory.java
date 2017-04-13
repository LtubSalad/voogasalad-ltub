package engine.spritecreation.factory;

import data.AttributeData;
import engine.sprite.Attribute;

/**
 * Interface for an AttributeFactory class. The goal of this class is to take in an AttributeData class instance for a specific
 * kind of attribute. This is the expected behavior for a certain kind of AttributeFactory
 * @author Matthew Tribby
 */
public interface AttributeFactory {

	/**
	 * Creates an attribute based on the AttributeData
	 * @param data 
	 * @return a newly configured Attribute
	 */
	public Attribute createAttribute(AttributeData data);
}
