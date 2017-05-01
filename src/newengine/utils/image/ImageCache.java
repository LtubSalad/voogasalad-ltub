package newengine.utils.image;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class ImageCache {

	public static final ImageCache CACHE = new ImageCache();
	
	private Map<String, Image> map = new HashMap<>();
	
	private ImageCache() {
		
	}
	
	public Image getImage(String filename) {
		if (map.containsKey(filename)) {
			return map.get(filename);
		} else {
			Image image = new Image(filename);
			map.put(filename, image);
			return image;
		}
	}
	
}
