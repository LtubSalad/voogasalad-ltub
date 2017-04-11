package engine.spritecreation;

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
		
		
		XStreamHandler XSH = new XStreamHandler(); 
		List<AttributeData> data = XSH.getScreenModelFile();
		
	}
}
