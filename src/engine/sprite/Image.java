package engine.sprite;

import java.io.InputStream;

public class Image {
	
	private String filename;
	
	public Image(String filename) {
		this.filename = filename;
	}

	public boolean isEmpty() {
		return filename == null;
	}
	
	public String getFileName() {
		return filename;
	}
	
	public InputStream getInputStream() {
		return getClass().getClassLoader().getResourceAsStream(filename);
	}
	
}
