package newengine.animation;


import java.util.List;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * @author Zhiyong
 * Given a list of Images, this class will generate the animation according
 * to the order in the list
 *
 */
public class ImageAnimation extends Transition {

	private final List<Image> imageList;

	private final int width;
	private final int height;
	private ImageView imageView;
	private int count;

	private int index;

	public ImageAnimation( List<Image> imageList, ImageView imageView, Duration duration, int width,   int height) {
		this.imageList = imageList;
		this.imageView = imageView;
		this.width     = width;
		this.height    = height;
		count = imageList.size();
		setCycleDuration(duration);
		setInterpolator(Interpolator.LINEAR);
	}

	protected void interpolate(double k) {
		index = Math.min((int) Math.round(k * count), count - 1);
		if(index < count){
			imageView.setImage(imageList.get(index));
			imageView.setViewport(new Rectangle2D(0, 0, width, height));
			index++;
			index = index % count;
		}

	}

}