package engine.sprite.images;

public class ImageSet {

	private LtubImage ltubImage;
	
	public ImageSet() {
		
	}
	
	public void setImage(LtubImage ltubImage) {
		this.ltubImage = ltubImage;
	}
	
	public LtubImage getImage(double angle, double dist) {
		return ltubImage;
	}
	
	
}
