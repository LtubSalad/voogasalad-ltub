/**
 * 
 */
package player.levelChoice;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * @author Zhiyong
 *
 */
public class LevelImageManager {
	
	public static final String IMAGE_NAME = "resources/select.png";
	
	private int width;
	private int height;
	
	public LevelImageManager(int width, int height){
		this.width = width;
		this.height = height;
		
	}
	
	public Rectangle getRectangle(){
		Rectangle rec = new Rectangle(100,-10*height,width,height);
		Image image = new Image(IMAGE_NAME);
		rec.setFill(new ImagePattern(image));
		return rec;
	}
	
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}

}
