package engine.camera;

public class GamePoint {
	
	private double x;
	private double y;
	
	public GamePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double x() {
		return x;
	}
	
	public double y() {
		return y;
	}
	
	public double distFrom(GamePoint other) {
		double xDiff = x - other.x;
		double yDiff = y - other.y;
		return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
	}

}
