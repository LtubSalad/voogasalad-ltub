package engine.spritecreation;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import data.AttributeData;
import engine.sprite.Sprite;

public class SpriteBuildingManager {
	private AttributeData data; 

	public SpriteBuildingManager(AttributeData spriteData) {
		this.data = spriteData; 
		makeSprite(); 
	}

	private void makeSprite() {
		SpriteBuilder SB = new SpriteBuilder(data);
		Sprite createdSprite = SB.getSprite(); 
		
	}



}
