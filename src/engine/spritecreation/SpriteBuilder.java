package engine.spritecreation;

import java.util.List;

import engine.sprite.Sprite; 
import data.AttributeData;



/**
 * CITE CODE
 * @author Tahia Emran (tse5) Matthew Tribby (mrt28)
 */
public class SpriteBuilder {
	private AttributeData myData; 
	private Sprite mySprite; 

	public SpriteBuilder(AttributeData spriteData){
		this.myData = spriteData; 
		mySprite = new Sprite(); 
		createAttributes();
	}

	private void createAttributes() {		  
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

