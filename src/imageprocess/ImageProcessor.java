/**
 * 
 */
package imageprocess;

import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;

/**
 * @author Zhiyong
 *
 */
public interface ImageProcessor extends Processor<Image> {
	
	Polygon getHull(Image I);

}
