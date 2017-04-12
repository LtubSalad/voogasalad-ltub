package engine.spritecreation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method; 

import data.AttributeData;

import engine.sprite.Attribute;
import engine.sprite.Sprite;
import engine.spritecreation.factory.AttributeFactory;

/**
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
