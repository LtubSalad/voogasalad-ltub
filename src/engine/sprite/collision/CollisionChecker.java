package engine.sprite.collision;

import java.util.List;
import java.util.stream.Collectors;

import bus.EventBus;
import commons.Point;
import engine.sprite.Sprite;
import javafx.scene.shape.Polygon;

public class CollisionChecker {

	private EventBus bus;
	
	public CollisionChecker(EventBus bus) {
		this.bus = bus;
	}
	
	private Polygon getFXPolygon(List<Point> bound, Point pos) {
		double[] points = new double[bound.size() * 2];
		for (int i = 0; i < bound.size(); i++) {
			points[i*2]   = bound.get(i).x() + pos.x();
			points[i*2+1] = bound.get(i).y() + pos.y();
		}
		return new Polygon(points);
	}
	
	private boolean collides(Sprite s1, Sprite s2) {
		Point pos1 = s1.getPos();
		CollisionBound bound1 = s1.getCollidable().get().getCollisionBound();
		Polygon polygon1 = getFXPolygon(bound1.getBoundPoints(), pos1);
		Point pos2 = s2.getPos();
		CollisionBound bound2 = s2.getCollidable().get().getCollisionBound();
		Polygon polygon2 = getFXPolygon(bound2.getBoundPoints(), pos2);
		return polygon1.intersects(polygon2.getBoundsInLocal());
	}
	
	public void checkCollision(List<Sprite> sprites) {
		List<Sprite> collidableSprites = sprites.stream().filter((s) -> {
			return s.getCollidable().isPresent() &&
					s.getCollidable().get().isCollidable();
		}).collect(Collectors.toList());
		for (Sprite s1 : collidableSprites) {
			for (Sprite s2 : collidableSprites) {
				if (s1 == s2) { continue; }
				if (collides(s1, s2)) {
					bus.emit(new CollisionEvent(CollisionEvent.ANY, s1, s2));
				}
			}
		}
	}
	
}
