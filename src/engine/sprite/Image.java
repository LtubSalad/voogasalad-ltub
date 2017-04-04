package engine.sprite;

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
	
}
