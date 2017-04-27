/**
 * 
 */
package newengine.animation;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import newengine.sprite.Sprite;
import newengine.sprite.components.Images;

/**
 * @author Zhiyong
 *
 */
public class SpriteAnimation {
	
	public static final int WIDTH    = 200;
	public static final int HEIGHT   = 200;
	
	private List<Sprite> sprites;
	
	public SpriteAnimation(List<Sprite> sprites){
		this.sprites = sprites;
	}
	
	public ImageView getImageView() {

		final ImageView imageView = new ImageView();		
		List<Image> list = getSpriteImage();
	
		final Animation animation = new ImageAnimation(
				list,
				imageView,
				Duration.millis(1000),
				WIDTH, HEIGHT
				);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();
		
		return imageView;
	}
	
	private List<Image> getSpriteImage() {
		List<Image> list = new ArrayList<>();
		
		for(Sprite sprite : sprites){
			if (sprite.getComponent(Images.TYPE).isPresent()) {
				list.add(sprite.getComponent(Images.TYPE).get().image().getFXImage());
			}
			
		}
		return list;
	}

	public List<Sprite> getSprites(){
		return sprites;
	}

}
