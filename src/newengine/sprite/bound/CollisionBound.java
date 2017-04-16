package newengine.sprite.bound;


import java.util.ArrayList;
import java.util.List;

import newengine.utils.image.LtubImage;
import newengine.utils.point.GamePoint;

public class CollisionBound {

	private List<GamePoint> bound;
	
	public CollisionBound(List<GamePoint> bound) {
		this.bound = bound;
	}
	public CollisionBound(LtubImage ltubImage) {
		bound = new ArrayList<GamePoint>();
		double w = ltubImage.width();
		double h = ltubImage.height();
		bound.add(new GamePoint(0, 0));
		bound.add(new GamePoint(w, 0));
		bound.add(new GamePoint(w, h));
		bound.add(new GamePoint(0, h));
	}
	
	public List<GamePoint> getBoundPoints() {
		return bound;
	}
	
}
