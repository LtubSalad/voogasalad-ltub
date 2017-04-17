package commons.point;

import commons.MathUtils;

/**
 * A pair of (x, y) coordinates for any points in the game.
 * The values of x and y are JavaFX pixel values. 
 * The {@code Point} class is a package-scope abstract class
 * which cannot be instantiated.
 * @author Yilin Gao
 *
 */
abstract class Point {

	private double x;
	private double y;
	
	/**
	 * Constructor of the {@code Point} class.
	 * @param x the x coordinate of the {@code Point}
	 * @param y the y coordinate of the {@code Point}
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}	
	/**
	 * Get the x coordinate of the {@code Point} represented as JavaFX pixel.
	 * @return double
	 */
	public double x() {
		return x;
	}
	/**
	 * Get the y coordinate of the {@code Point} represented as JavaFX pixel.
	 * @return double
	 */
	public double y() {
		return y;
	}
	/**
	 * Get the distance from the calling {@code Point} to another {@code Point}.
	 * @param other the other {@code Point} to compare with
	 * @return double
	 */
	public double distFrom(Point other) {
		double xDiff = x - other.x;
		double yDiff = y - other.y;
		return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
	}
	/**
	 * Compare if the calling {@code Point} is 
	 * at the same position (x and y values) as another {@code Point}.
	 * The comparison is approximate.
	 * @param other the other {@code Point} to compare with
	 * @return double
	 */
	public boolean approxEquals(Point other) {
		return (MathUtils.doubleEquals(distFrom(other), 0));
	}
	
}
