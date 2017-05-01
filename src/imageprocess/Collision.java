/**
 * 
 */
package imageprocess;

import javafx.scene.image.Image;
import newengine.sprite.Sprite;
import newengine.sprite.components.Images;
import newengine.sprite.components.Position;

/**
 * @author Zhiyong
 *
 */
public class Collision {
	
	public static boolean isCollided(Sprite s1, Sprite s2){
		
		Image image1 = s1.getComponent(Images.TYPE).get().image().getFXImage();
		Image image2 = s2.getComponent(Images.TYPE).get().image().getFXImage();
		
		double xPos1 = s1.getComponent(Position.TYPE).get().pos().x();
		double yPos1 = s1.getComponent(Position.TYPE).get().pos().y();
		
		double xPos2 = s2.getComponent(Position.TYPE).get().pos().x();
		double yPos2 = s2.getComponent(Position.TYPE).get().pos().y();
		
		CollisionOfImage c = new CollisionOfImage();
		
		
		return c.isCollided(image1, xPos1, yPos1, image2, xPos2, yPos2);
	}

}
