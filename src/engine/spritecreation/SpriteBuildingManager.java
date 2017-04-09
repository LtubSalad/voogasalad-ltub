package engine.spritecreation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SpriteBuildingManager {
	private static final DocumentBuilder D_BUILDER = getDocumentBuilder();
	private Document doc;
	
	public SpriteBuildingManager(String filePath) throws SAXException, IOException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		File xmlFile = new File(filePath);
		Document doc = D_BUILDER.parse(xmlFile);
		//There is a reason we normalize
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Sprite");
		
		for(int i = 0; i < nList.getLength(); i++){
			SpriteBuilder builder = new SpriteBuilder(D_BUILDER, nList.item(i));
		}
		
	}
	
	  private static DocumentBuilder getDocumentBuilder () {
	        try {
	            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        }
	        catch (ParserConfigurationException e) {
	           // throw new XMLException();
	        	return null;
	        }
	    }
		
}
