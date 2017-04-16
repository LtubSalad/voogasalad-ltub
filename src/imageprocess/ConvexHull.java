package imageprocess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javafx.geometry.Point2D;


/**
 *This class will be used to calculate the convex hull of
 *given Collection of Point2D.
 * @author Zhiyong
 */
public class ConvexHull {

	/**
	 * @param O initial point
	 * @param A a new point
	 * @param B another new point
	 * @return
	 * calculate the cross product the vector AO and BO
	 */
	public static double cross(Point2D O, Point2D A, Point2D B) {
		return (A.getX() - O.getX()) * (B.getY() - O.getY()) - (A.getY() - O.getY()) * (B.getX() - O.getX());
	}

	/**
	 * @param pointList A collection of Point2D objects
	 * @return a list of Point2D, which is the convex hull point
	 * create the convex hull from given point array
	 */
	public static List<Point2D> convex_hull(Collection<Point2D> pointList) {

		List<Point2D> points = new ArrayList<>(pointList);
		if (pointList.size() > 1) {
			int n = pointList.size(), k = 0;
			Point2D[] hullPoint = new Point2D[2 * points.size()];

			Collections.sort(points, (point1, point2) -> compare(point1, point2));

			// Build lower hull
			for (int i = 0; i < n; ++i) {
				while (k >= 2 && cross(hullPoint[k-2], hullPoint[k-1], points.get(i)) <= 0)
					k--;
				hullPoint[k++] = points.get(i);
			}

			// Build upper hull
			for (int i = n - 2, t = k + 1; i >= 0; i--) {
				while (k >= t && cross(hullPoint[k - 2], hullPoint[k-1], points.get(i)) <= 0)
					k--;
				hullPoint[k++] = points.get(i);
			}
			if (k > 1) {
				// remove non-hull vertices after k; remove k - 1 which is a duplicate
				hullPoint = Arrays.copyOfRange(hullPoint, 0, k - 1); 
			}
			return Arrays.asList(hullPoint);
		} else if (pointList.size()<= 1) {
			return points;
		} else{
			return null;
		}
	}

	private static int compare(Point2D point1, Point2D point2) {
		if(Math.round(point1.getX() - point2.getX())== 0){
			return (int) (point1.getY() - point2.getY());
		}else{
			return (int)(point1.getX() - point2.getX());
		}
	}

}