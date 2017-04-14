package engine.spritecreation;

import java.io.File;
import java.util.List;
import data.AttributeData;
import utilities.XStreamHandler;

public class AttributeBuilderTester {

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
