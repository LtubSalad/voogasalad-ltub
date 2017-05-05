/**
 * 
 */
package imageprocess;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Zhiyong
 * This class implements the ImageProcessor interface
 * Given an image, this class is used to resize, 
 *
 */
public class ImageTransformation implements ImageProcessor {

	/* (non-Javadoc)
	 * @see voogasalad.util.image.Processor#resize(java.lang.Object, double)
	 * resize of an image to a given scale of the original image
	 */
	@Override
	public Image resize(Image t, double scale) {		
		int targetWidth = (int)Math.round(t.getWidth() * scale);
		int targetHeight = (int)Math.round(t.getHeight() * scale);
		return resize(t, targetWidth, targetHeight, false);	
	}

	/* (non-Javadoc)
	 * @see voogasalad.util.image.Processor#resize(java.lang.Object, double, double, boolean)
	 * resize an image to some specific width and height
	 * one can choose whether the ration should be preserved
	 */
	@Override
	public Image resize(Image t, double targetWidth, double targetHeight, boolean preserveRatio) {
		ImageView imageView = new ImageView(t);
		imageView.setFitWidth(targetWidth);
		imageView.setFitHeight(targetHeight);
		imageView.setPreserveRatio(preserveRatio);
		return imageView.snapshot(null, null);	
	}

	// returns a Set of Coordinates that list all the pixels in an image that aren't transparent
	// the pixels contained in the Set follow the guideline of:
	// x,y where x is the x position of the pixel and y is the y position of the pixel
	@Override
	public Set<Point2D> getMask(Image image, Color color) {
		BufferedImage buffImg= SwingFXUtils.fromFXImage(image, null);
		Set<Point2D> mask = new HashSet<>();
		int pixel;
		boolean isTransparent, isWhite;
		for(int i =0; i < image.getWidth(); i++){
			for(int j = 0; j < image.getHeight(); j++){
				pixel = buffImg.getRGB(i,j);
				//check the transparency of the pixel at (i,j)
				isTransparent = (pixel >> 24) == 0x00;
				Color colorTest = new Color(pixel);
				isWhite = color.getRGB() == colorTest.getRGB();
				//transparency = transparency == 0 ? 0 : 1;
				if(!isTransparent && !isWhite){
					Point2D p = new Point2D(i,j);
					mask.add(p);
				}
			}
		}
		return mask;
	}
}