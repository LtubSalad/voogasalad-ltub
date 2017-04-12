/**
 * 
 */
package player.levelChoice;

import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import player.App;

/**
 * @author Zhiyong
 *
 */
public class LevelImageManager {

	private  ResourceBundle myResources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);
	
	private int width;
	private int height;
	
	public LevelImageManager(int width, int height){
		this.width = width;
		this.height = height;
		
	}
	
	public Rectangle getRectangle(){
		Rectangle rec = new Rectangle(100,-10*height,width,height);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(myResources.getString("selectImagePath")));
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
