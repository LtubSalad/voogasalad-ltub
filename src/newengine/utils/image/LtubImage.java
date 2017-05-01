package newengine.utils.image;

import java.io.InputStream;

import commons.RunningMode;
import commons.point.GamePoint;
import javafx.scene.image.Image;

public class LtubImage {

	public static final LtubImage EMPTY_IMAGE = new LtubImage();
	
	private String filename;
	//private Image image;
	/**
	 * {@code pivot} is the assigned center of an image.
	 * It is a relative position to the upper-left point of the image.
	 */
	private GamePoint pivot = null;
	
	LtubImage() { 
		
	}
	
	
	public LtubImage(String filename) {
		this.filename = filename;
	}

	public boolean isEmpty() {
		return filename == null;
	}
	
	public String getFileName() {
		return filename;
	}
	
	public Image getFXImage() {
		try {
			return ImageCache.CACHE.getImage(filename);
		} catch (NullPointerException e) {
			if (RunningMode.DEV_MODE) {
				System.out.println("DEV MODE stacktrace");
				System.out.println(this.filename);
				e.printStackTrace();
			}
		}	
		return null;
	}
	
	public double width() {
		Image image = getFXImage();
		if (image != null) {
			return (image.getWidth() >= 100) ? 100 : image.getWidth();
		}
		else {
			return 0;
		}
	}
	public double height() {
		Image image = getFXImage();
		if (image != null) {
			return (image.getHeight() >= 100) ? 100 : image.getHeight();
		}
		else {
			return 0;
		}
	}
	public void setImagePivot(GamePoint pivot) {
		this.pivot = pivot;
	}
	/**
	 * The default offset is half the size of the image.
	 * @return GamePoint
	 */
	public GamePoint getImagePivot() {
		if (pivot != null) {
			return pivot;
		} else {
			return new GamePoint(width() / 2, height() / 2);
		}
	}
	@Deprecated
	public InputStream getInputStream() {
		return getClass().getClassLoader().getResourceAsStream(filename);
	}
	
}
