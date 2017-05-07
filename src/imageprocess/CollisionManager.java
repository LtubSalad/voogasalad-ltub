package imageprocess;

import javafx.scene.image.Image;
import newengine.sprite.Sprite;
import newengine.sprite.components.Images;
import newengine.sprite.components.Position;

/**
 * @author Zhiyong
 *Check collision of two sprites
 *Here the sprite is defined in {@code newengine.sprite.Sprite}
 */
public class CollisionManager {

	public static boolean isCollided(Sprite s1, Sprite s2){
		//Get the image of the given sprite 
		Image image1 = s1.getComponent(Images.TYPE).get().image().getFXImage();
		Image image2 = s2.getComponent(Images.TYPE).get().image().getFXImage();
		//Get the position of the sprite s1
		double xPos1 = s1.getComponent(Position.TYPE).get().pos().x();
		double yPos1 = s1.getComponent(Position.TYPE).get().pos().y();
		//Get the position of the sprite s2
		double xPos2 = s2.getComponent(Position.TYPE).get().pos().x();
		double yPos2 = s2.getComponent(Position.TYPE).get().pos().y();
		// check collision
		CollisionOfImage c = new CollisionOfImage();		
		return c.isCollided(image1, xPos1, yPos1, image2, xPos2, yPos2);
	}
}