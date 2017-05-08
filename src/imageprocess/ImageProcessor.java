// This entire file is part of my masterpiece.
// Zhiyong Zhao

package imageprocess;

import java.util.Set;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Process Images, like resize, get the Mask of the binary representation
 * of a colorful image
 * @author Zhiyong
 */
public interface ImageProcessor extends Processor<Image>{	

	/**
	 * @param image
	 * @param color The default background color that need to remove
	 * @return returns a Set of Pair(i,j)  that list all the pixels in an image 
	 * that aren't transparent the pixels contained in the Set follow
	 * the guideline of: x,y where x is the absolute x position of 
	 * the pixel and y is the absolute y position of the pixel
	 * 
	 */
	Set<Point2D> getMask(Image image, Color color);
}