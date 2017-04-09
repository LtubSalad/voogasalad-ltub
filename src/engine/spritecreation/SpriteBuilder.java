package engine.spritecreation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



/**
 * CITE CODE
 * @author Tahia Emran (tse5) Matthew Tribby (mrt28)
 */
public class SpriteBuilder {
	private DocumentBuilder docBuilder;
	private Node rootNode;
	public static final List<String> ATTRIBUTE_TITLES = Arrays.asList(new String[]{
			"ConstantActor",
			"NodeHolder",
			"HealthHolder",
			"Attacker",
			"SpriteFactory"
	});
	
	  public SpriteBuilder(DocumentBuilder docBuilder, Node item) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		  this.docBuilder = docBuilder;
		  this.rootNode = item;
		  createAttributes();
	  }
	  
	  private void createAttributes() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		  if(rootNode.getNodeType() == Node.ELEMENT_NODE){
			  Element element = (Element) rootNode;
			  NodeList attributeList = element.getElementsByTagName("Attribute");
			  for(int i = 0; i < ATTRIBUTE_TITLES.size(); i++){
				  System.out.println(attributeList.item(0).getTextContent());
				  //Need to rebuild this AttributeBuilder fo default
				//  AttributeBuilder aBuilder = new AttributeBuilder(docBuilder, attributeList.item(i), ATTRIBUTE_TITLES.get(i));
			  }
		  }
		  else{
			  //Throw exception later
		  }
	  }

}