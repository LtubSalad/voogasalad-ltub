package engine.spritecreation;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import engine.sprite.Sprite; 

import data.AttributeData;



/**
 * CITE CODE
 * @author Tahia Emran (tse5) Matthew Tribby (mrt28)
 */
public class SpriteBuilder {
	//private DocumentBuilder docBuilder;
	//private Node rootNode;
	public static final String ATTRIBUTE_TAG = "Attribute";
	public static final List<String> ATTRIBUTE_TITLES = Arrays.asList(new String[]{
			"ConstantActor",
			"NodeHolder",
			"HealthHolder",
			"Attacker",
			"SpriteFactory", 
			"Weapon"
	});
	
	private AttributeData myData; 
	private Sprite mySprite; 
	/*  public SpriteBuilder(DocumentBuilder docBuilder, Node item)  {
		  this.docBuilder = docBuilder;
		  this.rootNode = item;
		  createAttributes();
	  }*/
	  

	  public SpriteBuilder(AttributeData spriteData){
		  this.myData = spriteData; 
		  mySprite = new Sprite(); 
		  addSpriteToModel();
	  }
	  
	  

	  private void addSpriteToModel() {
		// TODO Auto-generated method stub
		
	}



	private void createAttributes() {
/*		  if(rootNode.getNodeType() == Node.ELEMENT_NODE){
			  Element element = (Element) rootNode;
			  NodeList attributeList = element.getElementsByTagName(ATTRIBUTE_TAG);
			  Sprite newSprite = new Sprite(); 
			  for(int i = 0; i < ATTRIBUTE_TITLES.size(); i++){
				  AttributeData attributeData = new AttributeData(null);
				  AttributeBuilder aBuilder = new AttributeBuilder(attributeData);
				  aBuilder.configSprite(newSprite); // will want to hand this functionality to setter?
		
			  
			}*/
		  
		  	List<AttributeData> compositionAttributes = myData.getAttributes(); 
		  	for (AttributeData att : compositionAttributes){
		  		AttributeBuilder AB = new AttributeBuilder(myData);
		  		AB.configSprite(mySprite);
		  	}
		  }
	  
	public Sprite getSprite(){
		return mySprite; 
	}
	  
	 
	  
	  }

