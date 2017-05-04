package newengine.utils.checker;

import java.util.List;

import commons.MathUtils;
import commons.point.GamePoint;
import javafx.scene.shape.Polygon;
import newengine.sprite.Sprite;
import newengine.sprite.components.Collidable;
import newengine.sprite.components.Images;
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
		return collides(s1, s2, 0);
	}
	
	public static boolean collides(Sprite s1, Sprite s2, double extraDist) {
		if (!s1.getComponent(Position.TYPE).isPresent() ||
				!s2.getComponent(Position.TYPE).isPresent() ||
				!s1.getComponent(Collidable.TYPE).isPresent() ||
				!s2.getComponent(Collidable.TYPE).isPresent() || 
				!s1.getComponent(Images.TYPE).isPresent() ||
				!s2.getComponent(Images.TYPE).isPresent()) {
			return false;
		}
		GamePoint pos1 = s1.getComponent(Position.TYPE).get().pos();
		double heading1 = s1.getComponent(Position.TYPE).get().heading();
//		GamePoint pivot1 = s1.getComponent(Images.TYPE).get().image().getImagePivot();
//		pos1 = new GamePoint(pos1.x()+pivot1.x(), pos1.y()+pivot1.y());
		GamePoint pos2 = s2.getComponent(Position.TYPE).get().pos();
		double heading2 = s2.getComponent(Position.TYPE).get().heading();
//		GamePoint pivot2 = s2.getComponent(Images.TYPE).get().image().getImagePivot();
//		pos2 = new GamePoint(pos2.x()+pivot2.x(), pos2.y()+pivot2.y());
		List<GamePoint> bound1 = s1.getComponent(Collidable.TYPE).get().boundPoints();
		List<GamePoint> bound2 = s2.getComponent(Collidable.TYPE).get().boundPoints();
		Polygon polygon1 = getFXPolygon(bound1, pos1);
		polygon1.rotateProperty().set(heading1);
		Polygon polygon2 = getFXPolygon(bound2, pos2);
		if (!MathUtils.doubleEquals(0, extraDist)) {
			double dist = pos1.distFrom(pos2);
			polygon2 = getFXPolygon(bound2, new GamePoint(
					pos1.x() + (pos2.x() - pos1.x()) / dist * (dist + extraDist),
					pos1.y() + (pos2.y() - pos1.y()) / dist * (dist + extraDist)
					)); 
		}
		polygon2.rotateProperty().set(heading2);
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
	
	public static double findSepExtraDist(Sprite s1, Sprite s2) {
		double extraDist = 0;
		while (extraDist < 300) {
			if (!collides(s1, s2, extraDist)) {
				return extraDist;
			}
			extraDist += 5;
		}
		return 300;
	}
	

	
}
