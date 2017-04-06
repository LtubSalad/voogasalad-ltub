package engine.sprite.collision;


import java.util.ArrayList;
import java.util.List;

import commons.Point;
import engine.sprite.LtubImage;

public class CollisionBound {

	private List<Point> bound;
	
	public CollisionBound(List<Point> bound) {
		this.bound = bound;
	}
	public CollisionBound(LtubImage ltubImage) {
		bound = new ArrayList<Point>();
		double w = ltubImage.width();
		double h = ltubImage.height();
		bound.add(new Point(0, 0));
		bound.add(new Point(w, 0));
		bound.add(new Point(w, h));
		bound.add(new Point(0, h));
	}
	
	public List<Point> getBoundPoints() {
		return bound;
	}
	
}
