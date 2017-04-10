package engine.spritecreation;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Node;

import data.AttributeData;
import engine.sprite.Attribute;

public class AttributeBuilder {
	Attribute myAttribute; 
	
	public AttributeBuilder(DocumentBuilder docBuilder, Node item, String string) {
		// TODO Auto-generated constructor stub
	}
	
	public AttributeBuilder(AttributeData data){
		String attributeType = data.getType(); 
		String specificName = data.getImplementation(); 
		
	}

}
