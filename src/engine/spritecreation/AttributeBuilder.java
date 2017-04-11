package engine.spritecreation;

import java.lang.reflect.Constructor;

import data.AttributeData;

import data.AttributeData;
import engine.sprite.Attribute;
import engine.sprite.Sprite;


/**
 * @authors Tahia Emran 
 *
 */
public class AttributeBuilder {
	Attribute myAttribute; 
	String attributeType; 
	AttributeData dataToRead; 
	
	private static final String ATTRIBUTE_FILEPATH = "engine/sprite/";
	
	/*public AttributeBuilder(DocumentBuilder docBuilder, Node item, String string) {

	}*/
	public AttributeBuilder(AttributeData data){
		this.dataToRead = data; 
	}
	
	private void build() {
		attributeType = dataToRead.getType(); 
		String specificName = dataToRead.getImplementation();
		Class<?> attributeClass = Class.forName(ATTRIBUTE_FILEPATH + specificName);
		Constructor <?> ctor = attributeClass.getConstructor(AttributeData.class);
		Object obj = ctor.newInstance(dataToRead);
		myAttribute = (Attribute) obj; 
	}
	
	public void configSprite(Sprite s) {
		s.setAttribute(attributeType, myAttribute);
		
	}
	
	

}
