/**
 * 
 */
package imageprocess;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Zhiyong
 *
 */
public class ImageTransformation implements ImageProcessor {

	@Override
	public Image resize(Image t, double scale) {
		ImageView imageView = new ImageView(t);
	    int targetWidth = (int)Math.round(t.getWidth() * scale);
	    int targetHeight = (int)Math.round(t.getHeight() * scale);
	    imageView.setFitWidth(targetWidth);
	    imageView.setFitHeight(targetHeight);
	    return imageView.snapshot(null, null);		
	}
	
	@Override
	public Image resize(Image t, double targetWidth, double targetHeight, boolean preserveRatio) {
		ImageView imageView = new ImageView(t);
		imageView.setFitWidth(targetWidth);
		imageView.setFitHeight(targetHeight);
		imageView.setPreserveRatio(preserveRatio);
		return imageView.snapshot(null, null);	
	}
	
	// returns a HashSet of strings that list all the pixels in an image that aren't transparent
	// the pixels contained in the HashSet follow the guideline of:
	// x,y where x is the absolute x position of the pixel and y is the absolute y position of the pixel
	@Override
	public Set<Coordinate<Integer,Integer>> getMask(Image image ) {
		BufferedImage buffImg= SwingFXUtils.fromFXImage(image, null);
		Set<Coordinate<Integer,Integer>> mask = new HashSet<>();
		int pixel, transparency;
		for(int i =0; i < image.getWidth(); i++){
			for(int j = 0; j < image.getHeight(); j++){
				pixel = buffImg.getRGB(i,j);
				//check the transparency of the pixel at (i,j)
				transparency = (pixel >> 24) & 0xff;
				
				if(transparency != 0){
					mask.add(new Coordinate<Integer, Integer>(i,j));
				}
			}
		}
		return mask;
	}



}
