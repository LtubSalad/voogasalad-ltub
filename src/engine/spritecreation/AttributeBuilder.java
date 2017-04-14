package engine.spritecreation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method; 

import data.AttributeData;

import engine.sprite.Attribute;
import engine.sprite.Sprite;
import engine.spritecreation.factory.AttributeFactory;

/**
 * This class is used to build an attribute based on passed in AttributeData
 * 
 * @authors Tahia Emran and Matthew Tribby 
 * 
 * COMMENTED SECTIONS ARE FOR TESTING 
 *
 */
public class AttributeBuilder {
	private Attribute myAttribute; 
	private String attributeType; 
	private AttributeData dataToRead; 
	public static final String ATTRIBUTE_BASE_PATH = "engine.sprite.";
	public static final String ATTRIBUTE_BASE_PATH_2 = "engine.spritecreation.factory.";
	

	/**
	 * Initializes an AttributeBuilder which builds an attribute based on the corresponding data passed in
	 * Note this will only work if AttributeData's type and implementation correspond to existing attribute and follows
	 * the naming convention
	 * 
	 * Naming Convention:
	 * Attribute must be in class with path engine.sprite.attributetype.ImplementationName
	 * @param data Data to be used to build
	 * 
	 * TODO exception handling / checking if attribute exists
	 */
	public AttributeBuilder(AttributeData data){
		this.dataToRead = data; 
		build(); 
	}
	
	private void build() {
		attributeType = dataToRead.getName(); 
		//String specificName = dataToRead.getImplementation();
		String specificName = attributeType + "AttributeFactory";
		
		//String completePath = ATTRIBUTE_BASE_PATH + attributeType.toLowerCase() + "." + specificName;
		String completePath = ATTRIBUTE_BASE_PATH_2 + specificName; 
		
		try {
			Class <?> attributeClass = Class.forName(completePath);
			//Constructor <?> ctor = attributeClass.getConstructor(AttributeData.class);
			Constructor <?> ctor = attributeClass.getConstructor();
			//Object obj = ctor.newInstance(dataToRead);
			//myAttribute = (Attribute) obj;
			AttributeFactory factory = (AttributeFactory) ctor.newInstance();
			myAttribute = factory.createAttribute(dataToRead);
		} 
		
		catch (Exception e) {
			throw new SpriteCreationException("Attribute class not found " + specificName);
		}	
	}
	
	/**
	 * Configures a passed in sprite by setting its attribute to this newly created attribute
	 * @param s Sprite to configure
	 * 
	 * TODO make this work without having to give reference to sprite to this class, definitely possible
	 */
	public void configSprite(Sprite s) {
		String methodName = "set" + attributeType; 
		try {
			Method setter = s.getClass().getMethod(methodName, Class.forName(ATTRIBUTE_BASE_PATH + attributeType.toLowerCase() + "." + attributeType));
			setter.invoke(s, myAttribute);
		} catch (Exception e) {
			throw new SpriteCreationException ("can't find method " + methodName);
		}
		
	}
	
	

}
