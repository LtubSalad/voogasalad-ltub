package engine.sprite.nodeholder;

import data.AttributeData;
import engine.sprite.images.LtubImage;

public class ImageNodeHolder extends NodeHolder {
	private LtubImage image; 
	public ImageNodeHolder(AttributeData data) {
		super(data);
		System.out.println("It worked");
//		String filePath = data.getVariable("filepath");
//		image = new LtubImage(filePath);
	}
	// need to set image = the one we get from data 
	
	public LtubImage getImage(){
		return image; 
	}
	
}
