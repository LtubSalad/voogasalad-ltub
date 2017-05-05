// This entire file is part of my masterpiece.
// Zhiyong Zhao
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

	private List<Point2D> pointList;
	private Collection<Point2D> pointSet;

	public ConvexHull(Collection<Point2D> pointSet){
		this.pointSet = pointSet;
		this.pointList = getHull();
	}

	/**
	 * @param pointList A collection of Point2D objects
	 * @return a list of Point2D, which is the convex hull point
	 * create the convex hull from given point array
	 */
	public List<Point2D> getConvexHull() {

		if (pointList.size() > 1) {
			int n = pointList.size(), k = 0;
			Point2D[] hullPoint = new Point2D[2 * pointList.size()];

			Collections.sort(pointList, (point1, point2) -> compare(point1, point2));
			// Build lower hull
			for (int i = 0; i < n; ++i) {
				while (k >= 2 && cross(hullPoint[k-2], hullPoint[k-1], pointList.get(i)) <= 0)
					k--;
				hullPoint[k++] = pointList.get(i);
			}
			// Build upper hull
			for (int i = n - 2, t = k + 1; i >= 0; i--) {
				while (k >= t && cross(hullPoint[k - 2], hullPoint[k-1], pointList.get(i)) <= 0)
					k--;
				hullPoint[k++] = pointList.get(i);
			}
			if (k > 1) {
				// remove non-hull vertices after k; remove k - 1 which is a duplicate
				hullPoint = Arrays.copyOfRange(hullPoint, 0, k - 1); 
			}
			return Arrays.asList(hullPoint);
		} else if (pointList.size()<= 1) {
			return pointList;
		} else{
			return null;
		}
	}

	/**
	 * @param pointSet
	 * @return list of Point2D object, which is the hull of the given pointSet
	 * create the hull of the given points
	 */
	private List<Point2D> getHull(){
		pointList = new ArrayList<>();
		//filter the pointSet that points are HullPoint
		//Use the stream to filter and then for each to add the point to the pointList
		pointSet.stream().filter(e -> isHullPoint(e, pointList)).forEach(e -> pointList.add(e));;
		return pointList;
	}

	private int compare(Point2D point1, Point2D point2) {
		if(Math.round(point1.getX() - point2.getX())== 0){
			return (int) (point1.getY() - point2.getY());
		}else{
			return (int)(point1.getX() - point2.getX());
		}
	}

	private boolean isHullPoint(Point2D point, Collection<Point2D> pointSet){
		Point2D left = new Point2D(point.getX() - 1, point.getY());
		Point2D top = new Point2D(point.getX(), point.getY() - 1);
		Point2D right = new Point2D(point.getX() + 1, point.getY());
		Point2D bottom = new Point2D(point.getX(), point.getY() + 1);
		//check whether the point is an interior point
		return !(pointSet.contains(left) && pointSet.contains(top) &&
				pointSet.contains(right) && pointSet.contains(bottom));		
	}

	/**
	 * @param O initial point
	 * @param A a new point
	 * @param B another new point
	 * @return
	 * calculate the cross product the vector AO and BO
	 */
	private double cross(Point2D O, Point2D A, Point2D B) {
		return (A.getX() - O.getX()) * (B.getY() - O.getY()) - (A.getY() - O.getY()) * (B.getX() - O.getX());
	}
}