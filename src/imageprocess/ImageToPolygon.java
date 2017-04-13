/**
 * 
 */
package imageprocess;

import java.util.Set;
import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;

/**
 * @author Zhiyong
 *
 */
public class ImageToPolygon {

	private Image image;
	public ImageToPolygon(Image image){
		this.image =image;
		//TODO : change the default image
	}

	public Polygon getPolygon(){
		ImageTransformation transformation = new ImageTransformation();
		Set<Point> set = transformation.getMask(image);
		Point[] points = new Point[set.size()];
		int index =0;
		for(Point p : set){
			points[index] =p;
			index++;
		}

		Point[] convexHullPoint =  ConvexHull.convex_hull(points); 

		double[] pointsToDraw = new double[2 * convexHullPoint.length];
		int k = 0;
		for(Point p : convexHullPoint){
			pointsToDraw[k] = p.getX();
			pointsToDraw[k + 1] = p.getY();
			k += 2;
		}

		Polygon pol = new Polygon( pointsToDraw);		
		return pol;
	}
}
