/**
 * 
 */
package imageprocess;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;

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

	@Override
	public Image rotate(Image t, double angle) {
		BufferedImage buffImg= SwingFXUtils.fromFXImage(t, null);
		buffImg = getRotatedImage(buffImg, angle); 
		t = SwingFXUtils.toFXImage(buffImg, null);
		
		return t;
	}

	@Override
	public Polygon getHull(Image I) {
		// TODO Auto-generated method stub
		return null;
	}


	private BufferedImage getRotatedImage(BufferedImage bufferedImage, double angle) {
	     AffineTransform transform = new AffineTransform();
	     transform.rotate(angle);
	     AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
	     bufferedImage = op.filter(bufferedImage, null);
	     return bufferedImage;
	}

}
