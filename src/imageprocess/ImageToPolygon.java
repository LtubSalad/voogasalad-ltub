// This entire file is part of my masterpiece.
// Zhiyong Zhao
package imageprocess;

import java.util.List;
import java.util.Set;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
/**
 * 
 * There are two choices to create a hull of the image
 * one is convex hull of an image, another is the plain
 * hull of an image. One can choose one of those by commenting
 *another line
 * @author Zhiyong
 *
 */
public class ImageToPolygon {
	private Image image;
	private Color backgroundColor;

	public ImageToPolygon(Image image, Color color){
		this.image =image;
		this.backgroundColor = color;
	}

	/**
	 * @return Polygon
	 * Create a polygon hull of the given image by deleting the transparent pixels
	 * There are two methods: convex_hull and hull
	 */
	public Polygon getPolygon(){
		ImageTransformation transformation = new ImageTransformation();
		Set<Point2D> set = transformation.getMask(image, backgroundColor);			
		ConvexHull convexHull = new ConvexHull(set);
		//Get the convex hull point of the outline of the image
		List<Point2D> pointList = convexHull.getConvexHull();
		double[] pointsToDraw = new double[2 * pointList.size()];
		int k = 0;
		for(Point2D p : pointList){
			pointsToDraw[k] = p.getX();
			pointsToDraw[k + 1] = p.getY();
			k += 2;
		}
		return new Polygon( pointsToDraw);
	}
}