package engine.spritecreation;

import java.util.List;

import engine.sprite.Sprite;
import engine.sprite.images.ImageSet;
import engine.sprite.images.LtubImage;
import data.AttributeData;



/**
 * This class is reponsible for building a singular sprite and assembling its components (Attribute classes)
 * @author Tahia Emran (tse5) Matthew Tribby (mrt28)
 */
public class SpriteBuilder {
	private AttributeData myData; 
	private Sprite mySprite; 

	/**
	 * Constructor, builds a sprite based on AttributeData which contains all the sprites attributes as sub-attributes kept
	 * in AttributeData
	 * @param spriteData
	 */
	public SpriteBuilder(AttributeData spriteData){
		this.myData = spriteData; 
		mySprite = new Sprite(); 
		createAttributes();
	}
	
	
	private void createAttributes() {		  
		List<AttributeData> compositionAttributes = myData.getAttributes(); 
		
		for (AttributeData att : compositionAttributes){
			if(!att.getName().equals("NodeHolder")){
				AttributeBuilder AB = new AttributeBuilder(att);
				AB.configSprite(mySprite);
			}else{ // TODO: temporary, need to better integrate
				System.out.println("image set");
				mySprite.setImageSet(new ImageSet(new LtubImage("images/characters/" + att.getVariable("filepath"))));
			}
		}
	}
	
	/**
	 * Returns the sprite that is created 
	 * @return Sprite
	 */
	public Sprite getSprite(){
		return mySprite; 
	}



}

