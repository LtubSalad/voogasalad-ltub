package imageprocess;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * @author Zhiyong
 * Check collision of two images with different locations
 * Here the location means the pixel of the left corner point of the given image
 *Use the interface {@code imageprocess.ImageTransformation}
 */
public class CollisionOfImage extends ImageTransformation{
	public static final Color COLOR = Color.WHITE;
	// Returns true if there is a collision between Image a at location(xPos1, yPos1) and Image b at location(xPos2, yPos2)
	public boolean isCollided(Image a, double xPos1, double yPos1, Image b, double xPos2, double yPos2){
		//Get the Polygon contour of the main character in the image with a background color COLOR
		ImageToPolygon imA = new ImageToPolygon(a, COLOR);		
		ImageToPolygon imB = new ImageToPolygon(b, COLOR);

		Polygon polygonA = imA.getPolygon();	
		Polygon polygonB = imB.getPolygon();
		//translate the image to correct location 
		polygonA.setTranslateX(xPos1);
		polygonA.setTranslateY(yPos1);

		polygonB.setTranslateX(xPos2);
		polygonB.setTranslateY(yPos2);
		//check collision of the two nodes
		return polygonA.getBoundsInParent().intersects(polygonB.getBoundsInParent());
	}
}