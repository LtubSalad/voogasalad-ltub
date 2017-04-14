package engine.spritecreation;

import java.io.File;
import java.util.List;
import data.AttributeData;
import utilities.XStreamHandler;

/**
 * Tester class for AttributeBuilder
 * @author Matthew Tribby, Tahia Emran
 * 
 * TODO convert to JUnit test
 */
public class AttributeBuilderTester {
	/**
	 * Tests to ensure that if a xml file is passed/saved from authoring environment that it correctly creates attributes
	 * @param args
	 */
	public static void main (String[] args){
		String fileName = "data/attributeSkeletons/userCreatedAttributes/Test_Demo.xml";
		File file = new File(fileName);
		XStreamHandler XSH = new XStreamHandler(); 
		List<AttributeData> data = XSH.getScreenModel(file);	
		SpriteBuildingManager manager = new SpriteBuildingManager(); 		
		for(AttributeData ad :data){
			manager.createSprite(ad);
		}
	}
}
