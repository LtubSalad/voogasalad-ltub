package engine.spritecreation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method; 

import data.AttributeData;

import data.AttributeData;
import engine.sprite.Attribute;
import engine.sprite.Sprite;
import engine.sprite.WalkerMovable;




/**
 * @authors Tahia Emran and Matthew Tribby 
 *
 */
public class AttributeBuilder {
	private Attribute myAttribute; 
	private String attributeType; 
	private AttributeData dataToRead; 
	
	
	String implementation; 
	private static final String ATTRIBUTE_FILEPATH = "engine.sprite.";
	
	/*public AttributeBuilder(DocumentBuilder docBuilder, Node item, String string) {

	}*/
	public AttributeBuilder(AttributeData data){
		this.dataToRead = data; 
	}
	
	public AttributeBuilder(String type){
		this.attributeType = type; 
		//this.implementation = implementation; 
	}
	
	public void build(String implementation2) {		
		try {
			String attemptedName = ATTRIBUTE_FILEPATH + implementation2; 
			System.out.print("attempted filepath " + attemptedName);
			/*WalkerMovable WM = new WalkerMovable(); 
			System.out.println(WM.getClass().getName());*/
			Class <?> attributeClass = Class.forName(ATTRIBUTE_FILEPATH + implementation2);
			Constructor <?> ctor = attributeClass.getConstructor();
			Object obj = ctor.newInstance();
			myAttribute = (Attribute) obj; 
		} 
		catch (Exception e) {
			throw new SpriteCreationException("Attribute class not found " + implementation);
		}		
	}

	private void build() {
		attributeType = dataToRead.getName(); 
		String specificName = dataToRead.getImplementation();
		
		try {
			Class <?> attributeClass = Class.forName(ATTRIBUTE_FILEPATH + specificName);
			Constructor <?> ctor = attributeClass.getConstructor(AttributeData.class);
			Object obj = ctor.newInstance(dataToRead);
			myAttribute = (Attribute) obj; 
		} 
		catch (Exception e) {
			throw new SpriteCreationException("Attribute class not found " + specificName);
		}
		
	}
	
	public void configSprite(Sprite s) {
		s.setAttribute(attributeType, myAttribute);
		String methodName = "set" + attributeType; 
		System.out.print("method name is " + methodName);
		try {
			Method setter = s.getClass().getMethod(methodName, Attribute.class);
			setter.invoke(s, myAttribute);
		} catch (Exception e) {
			throw new SpriteCreationException ("can't find method " + methodName);
		}
		
	}
	
	

}
