/**
 * 
 */
package imageprocess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.geometry.Point2D;

/**
 * This class is used to calculate the hull of the 
 * given pointSet
 * @author Zhiyong
 *
 */
public class Hull {


	/**
	 * @param pointSet
	 * @return list of Point2D object, which is the hull of the given pointSet
	 * create the hull of the given points
	 */
	public static List<Point2D> getHull(Collection<Point2D> pointSet){
		List<Point2D> pointList = new ArrayList<>();

		for(Point2D point : pointSet){
			Point2D left = new Point2D(point.getX() - 1, point.getY());
			Point2D top = new Point2D(point.getX(), point.getY() - 1);
			Point2D right = new Point2D(point.getX() + 1, point.getY());
			Point2D bottom = new Point2D(point.getX(), point.getY() + 1);
			//check whether the point is an interior point
			if(pointSet.contains(left) && pointSet.contains(top) &&
					pointSet.contains(right) && pointSet.contains(bottom)){
				continue;
			}
			pointList.add(point);
		}

		return pointList;
	}

}