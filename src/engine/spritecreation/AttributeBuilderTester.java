package engine.spritecreation;

import java.io.File;
import java.util.List;

import data.AttributeData;
import engine.sprite.Sprite;
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
//		AttributeBuilder AB = new AttributeBuilder("Movable");
//		Sprite s = new Sprite(); 
//		AB.build("WalkerMovable");
//		AB.configSprite(s);
//		System.out.println(s.getMovable().getClass().getName());
		String fileName = "data/XMLFILES/test_setup.xml";
		File file = new File(fileName);
		XStreamHandler XSH = new XStreamHandler(); 
		List<AttributeData> data = XSH.getScreenModel(file);
//		for (AttributeData ad : data){
//			System.out.println("attribute name is " + ad.getName());
//			List<AttributeData> subAttributes = ad.getAttributes();
//			for(AttributeData sub : subAttributes){
//			System.out.println("       " +  sub.getName());
//			}
//			//PASS SUBATTRIBUTE FOR SETUP
//		}
		
//		AttributeData testAttribute = data.get(0);
//		List<AttributeData> subs = testAttribute.getAttributes();
//		
		SpriteBuildingManager manager = new SpriteBuildingManager(); 
		//manager.createSprite(testAttribute); 
		
		for(AttributeData ad :data){
			manager.createSprite(ad);
		}
	}
}
