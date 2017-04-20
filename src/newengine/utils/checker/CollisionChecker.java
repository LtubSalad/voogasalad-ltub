package newengine.utils.checker;

import java.util.List;

import commons.MathUtils;
import commons.point.GamePoint;
import javafx.scene.shape.Polygon;
import newengine.sprite.Sprite;
import newengine.sprite.components.Collidable;
import newengine.sprite.components.Position;

public class CollisionChecker {

	private CollisionChecker() { }
	
	private static Polygon getFXPolygon(List<GamePoint> bound, GamePoint pos) {
		double[] points = new double[bound.size() * 2];
		for (int i = 0; i < bound.size(); i++) {
			points[i*2]   = bound.get(i).x() + pos.x();
			points[i*2+1] = bound.get(i).y() + pos.y();
		}
		return new Polygon(points);
	}
	
	public static boolean collides(Sprite s1, Sprite s2) {
		if (!s1.getComponent(Position.TYPE).isPresent() ||
				!s2.getComponent(Position.TYPE).isPresent() ||
				!s1.getComponent(Collidable.TYPE).isPresent() ||
				!s2.getComponent(Collidable.TYPE).isPresent()) {
			return false;
		}
		GamePoint pos1 = s1.getComponent(Position.TYPE).get().pos();
		GamePoint pos2 = s2.getComponent(Position.TYPE).get().pos();
		List<GamePoint> bound1 = s1.getComponent(Collidable.TYPE).get().boundPoints();
		List<GamePoint> bound2 = s2.getComponent(Collidable.TYPE).get().boundPoints();
		Polygon polygon1 = getFXPolygon(bound1, pos1);
		Polygon polygon2 = getFXPolygon(bound2, pos2);
		return polygon1.intersects(polygon2.getBoundsInLocal());
	}
	
	public static boolean collidesOnTarget(Sprite s1, Sprite s2){
		if (!s1.getComponent(Position.TYPE).isPresent() ||
				!s2.getComponent(Position.TYPE).isPresent() ||
				!s1.getComponent(Collidable.TYPE).isPresent() ||
				!s2.getComponent(Collidable.TYPE).isPresent()) {
			return false;
		}
		GamePoint g1 = s1.getComponent(Position.TYPE).get().pos();
		GamePoint g2 = s2.getComponent(Position.TYPE).get().pos();
		return g1.distFrom(g2) < 0.5;
	}
	
	

	
}
