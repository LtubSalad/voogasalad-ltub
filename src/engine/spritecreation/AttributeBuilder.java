package engine.spritecreation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method; 

import data.AttributeData;

import engine.sprite.Attribute;
import engine.sprite.Sprite;

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
	//private String implementation; 
	private static final String ATTRIBUTE_BASE_PATH = "engine.sprite.";
	

	public AttributeBuilder(AttributeData data){
		this.dataToRead = data; 
		build(); 
	}
	
/*	public AttributeBuilder(String type){
		this.attributeType = type; 
		//this.implementation = implementation; 
	}
	
	public void build(String implementation2) {		
		try {
			String attemptedName = ATTRIBUTE_BASE_PATH + implementation2; 
			System.out.println("attempted filepath " + attemptedName);
			Class <?> attributeClass = Class.forName(ATTRIBUTE_BASE_PATH + attributeType.toLowerCase() + "." + implementation2);
			Constructor <?> ctor = attributeClass.getConstructor();
			Object obj = ctor.newInstance();
			myAttribute = (Attribute) obj; 
		} 
		catch (Exception e) {
			throw new SpriteCreationException("Attribute class not found " + implementation);
		}		
	}
*/
	private void build() {
		attributeType = dataToRead.getName(); 
		//String specificName = dataToRead.getImplementation();
		String specificName = dataToRead.getAttributes().get(0).getName();
		
		String completePath = ATTRIBUTE_BASE_PATH + attributeType.toLowerCase() + "." + specificName;
		
		try {
			Class <?> attributeClass = Class.forName(completePath);
			Constructor <?> ctor = attributeClass.getConstructor(AttributeData.class);
			Object obj = ctor.newInstance(dataToRead);
			myAttribute = (Attribute) obj; 
			System.out.println("object created!!!!!!");
		} 
		catch (Exception e) {
			throw new SpriteCreationException("Attribute class not found " + specificName);
		}
		
	}
	
	public void configSprite(Sprite s) {
		String methodName = "set" + attributeType; 
		System.out.println("method name is " + methodName);
		try {
			Method setter = s.getClass().getMethod(methodName, Class.forName(ATTRIBUTE_BASE_PATH + attributeType.toLowerCase() + "." + attributeType));
			setter.invoke(s, myAttribute);
		} catch (Exception e) {
			throw new SpriteCreationException ("can't find method " + methodName);
		}
		
	}
	
	

}
