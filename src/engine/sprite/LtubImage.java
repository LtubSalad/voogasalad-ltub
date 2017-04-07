package engine.sprite;

import java.io.InputStream;

import commons.RunningMode;
import engine.camera.GamePoint;
import javafx.scene.image.Image;

public class LtubImage {

	public static final LtubImage EMPTY_IMAGE = new LtubImage();
	
	private String filename;
	private Image image;
	// imageOffSet is a relative position of an image to its upper-left point.
	private GamePoint imageOffset = null;
	
	LtubImage() { }
	
	public LtubImage(String filename) {
		this.filename = filename;
		try {
			image = new Image(getInputStream());
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
		return image;
	}
	
	public double width() {
		return image != null ? image.getWidth() : 0;
	}
	public double height() {
		return image != null ? image.getHeight() : 0;
	}
	public void setImageOffset(GamePoint offset) {
		this.imageOffset = offset;
	}
	/**
	 * The default offset is half the size of the image.
	 * @return GamePoint
	 */
	public GamePoint getImageOffset() {
		if (imageOffset != null) {
			return imageOffset;
		} else {
			return new GamePoint(width() / 2, height() / 2);
		}
	}
	public InputStream getInputStream() {
		return getClass().getClassLoader().getResourceAsStream(filename);
	}
	
}
