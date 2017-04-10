package engine.spritecreation;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import data.AttributeData;



/**
 * CITE CODE
 * @author Tahia Emran (tse5) Matthew Tribby (mrt28)
 */
public class SpriteBuilder {
	private DocumentBuilder docBuilder;
	private Node rootNode;
	public static final String ATTRIBUTE_TAG = "Attribute";
	public static final List<String> ATTRIBUTE_TITLES = Arrays.asList(new String[]{
			"ConstantActor",
			"NodeHolder",
			"HealthHolder",
			"Attacker",
			"SpriteFactory"
	});
	
	  public SpriteBuilder(DocumentBuilder docBuilder, Node item)  {
		  this.docBuilder = docBuilder;
		  this.rootNode = item;
		  createAttributes();
	  }
	  
	  private void createAttributes() {
		  if(rootNode.getNodeType() == Node.ELEMENT_NODE){
			  Element element = (Element) rootNode;
			  NodeList attributeList = element.getElementsByTagName(ATTRIBUTE_TAG);
			  for(int i = 0; i < ATTRIBUTE_TITLES.size(); i++){
				  // TODO make it work for attribute data
				  AttributeData attributeData = new AttributeData(null);
				  AttributeBuilder aBuilder = new AttributeBuilder(attributeData);
			  }
		  }
	  }

}