package engine.sprite.nodeholder;

import data.AttributeData;
import engine.sprite.images.LtubImage;

/**
 * Extension of NodeHolder, currently built just for testing
 * Designed to hold an image as an attribute for the sprite
 * TODO actually make this a real, working class
 */
public class ImageNodeHolder extends NodeHolder {
	private LtubImage image; 
	
	/**
	 * Constructor which takes in attribute data and then creates an image for itself
	 * @param data AttributeData class
	 * 
	 * TODO make new constructor not dependent on this AttributeData
	 */
	public ImageNodeHolder(AttributeData data) {
		super(data);
		System.out.println("It worked");
//		String filePath = data.getVariable("filepath");
//		image = new LtubImage(filePath);
	}
	// need to set image = the one we get from data 
	
	/**
	 * Returns the current image 
	 * @return image
	 */
	public LtubImage getImage(){
		return image; 
	}
	
}
