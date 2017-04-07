package engine.sprite.images;

import java.io.InputStream;

import commons.RunningMode;
import engine.camera.GamePoint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LtubImage {

	public static final LtubImage EMPTY_IMAGE = new LtubImage();
	
	private String filename;
	private Image imageView;
	// imageOffSet is a relative position of an image to its upper-left point.
	private GamePoint pivot = null;
	
	LtubImage() { }
	
	public LtubImage(String filename) {
		this.filename = filename;
		try {
			imageView = new Image(getInputStream());
		} catch (NullPointerException e) {
			if (RunningMode.DEV_MODE) {
				e.printStackTrace();
			}
		}
	}

	public boolean isEmpty() {
		return filename == null;
	}
	
	public String getFileName() {
		return filename;
	}
	
	public Image getFXImage() {
		return imageView;
	}
	
	public double width() {
		return imageView != null ? imageView.getWidth() : 0;
	}
	public double height() {
		return imageView != null ? imageView.getHeight() : 0;
	}
	public void setImageOffset(GamePoint offset) {
		this.pivot = offset;
	}
	/**
	 * The default offset is half the size of the image.
	 * @return GamePoint
	 */
	public GamePoint getImageOffset() {
		if (pivot != null) {
			return pivot;
		} else {
			return new GamePoint(width() / 2, height() / 2);
		}
	}
	public InputStream getInputStream() {
		return getClass().getClassLoader().getResourceAsStream(filename);
	}
	
}
