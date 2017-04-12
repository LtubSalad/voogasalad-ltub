package engine.spritecreation;

import java.io.File;
import java.util.List;

import data.AttributeData;
import engine.sprite.Sprite;
import utilities.XStreamHandler;

public class AttributeBuilderTester {

	
	public static void main (String[] args){
//		AttributeBuilder AB = new AttributeBuilder("Movable");
//		Sprite s = new Sprite(); 
//		AB.build("WalkerMovable");
//		AB.configSprite(s);
//		System.out.println(s.getMovable().getClass().getName());
		String fileName = "data/XMLFILES/sample_tower.xml";
		File file = new File(fileName);
		XStreamHandler XSH = new XStreamHandler(); 
		AttributeData data = XSH.getAttributeFromFile(file);
		//for (AttributeData ad : data){
			System.out.println("attribute name is " + data.getName());
			List<AttributeData> subAttributes = data.getAttributes();
			for(AttributeData sub : subAttributes){
			System.out.println("       " +  sub.getName());
		//	}
			
			
			//PASS SUBATTRIBUTE FOR SETUP
		}
		
		AttributeData testAttribute = data;//.get(0);
		List<AttributeData> subs = testAttribute.getAttributes();
		
		// 4 my sanity 
		AttributeData passedAttribute = subs.get(0);
		
		SpriteBuildingManager manager = new SpriteBuildingManager(); 
		manager.createSprite(testAttribute); 
		
		
		
	}
}
