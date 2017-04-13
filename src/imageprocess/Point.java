/**
 * 
 */
package imageprocess;

/**
 * @author Zhiyong
 *
 */

class Point implements Comparable<Point> {
	double x, y;
	
	public Point(double x,double y){
		this.x = x;
		this.y = y;
	}

	
	@Override
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
	

}