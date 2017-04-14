/**
 * 
 */
package imageprocess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Zhiyong
 *
 */
public class Hull {
	
	
	public static Point[] getHull(Collection<Point> pointSet ){
		List<Point> pointList = new ArrayList<>();
		
		for(Point point : pointSet){
			Point left = new Point(point.getX() - 1, point.getY());
			Point top = new Point(point.getX(), point.getY() - 1);
			Point right = new Point(point.getX() + 1, point.getY());
			Point bottom = new Point(point.getX(), point.getY() + 1);
			if(pointSet.contains(left) && pointSet.contains(top) &&
					pointSet.contains(right) && pointSet.contains(bottom)){
				continue;
			}
			pointList.add(point);
		}
		Point[] points = new Point[pointList.size()];
		for(int i = 0; i < points.length; i++){
			points[i] = pointList.get(i);
		}
				
		return points;
	}
	
	
}
