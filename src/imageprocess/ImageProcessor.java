/**
 * 
 */
package imageprocess;

import java.util.Set;

import javafx.scene.image.Image;

/**
 * @author Zhiyong
 *
 */
public interface ImageProcessor extends Processor<Image>{
	
	/**
	 * @param I
	 * @return
	 * returns a Set of Pair(i,j)  that list all the pixels in an image 
	 * that aren't transparent the pixels contained in the Set follow
	 * the guideline of: x,y where x is the absolute x position of 
	 * the pixel and y is the absolute y position of the pixel
	 */
	Set<Point> getMask(Image image);
	

}
