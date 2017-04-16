package newengine.sprite.image;

import newengine.utils.image.LtubImage;

public class ImageSet {

	private LtubImage ltubImage;
	
	public ImageSet() {
		
	}
	
	public ImageSet(LtubImage ltubImage){
		this();
		setImage(ltubImage);
	}
	
	public void setImage(LtubImage ltubImage) {
		this.ltubImage = ltubImage;
	}
	
	/**
	 * Return the corresponding sprite {@code LtubImage} instance
	 * according to the current moving distance and angle of the sprite.
	 * @param angle
	 * @param dist
	 * @return
	 */
	public LtubImage getImage(double angle, double dist) {
		return ltubImage;
	}
	
	
}
