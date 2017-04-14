/**
 * 
 */
package imageprocess;

import java.util.Comparator;

/**
 * @author Zhiyong
 *
 */

class Point implements Comparator<Point> , Comparable<Point>{
	private double  x, y;
	
	public Point(double x,double y){
		this.x = x;
		this.y = y;
	}

	
	
	public int compareTo(Point p) {
		if (Math.round(this.x - p.x) == 0) {
			return (int) (this.y - p.y);
			
		} else {
			return (int) (this.x - p.x);
		}
	}

	public String toString() {
		return "("+x + "," + y+")";
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	
	public boolean equals(Point p){
		return this.toString().equals(p.toString());
	}


	@Override
	public int compare(Point p, Point q) {
		
		return this.compareTo(q);
	}
	

}