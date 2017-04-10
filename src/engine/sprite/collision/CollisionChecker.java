package engine.sprite.collision;

import java.util.List;
import java.util.stream.Collectors;

import bus.EventBus;
import engine.camera.GamePoint;
import engine.sprite.Sprite;
import engine.sprite.attack.Bullet;
import engine.sprite.health.DecrementHealthEvent;
import javafx.scene.shape.Polygon;

public class CollisionChecker {

	private EventBus bus;
	
	public CollisionChecker(EventBus bus) {
		this.bus = bus;
	}
	
	private Polygon getFXPolygon(List<GamePoint> bound, GamePoint pos) {
		double[] points = new double[bound.size() * 2];
		for (int i = 0; i < bound.size(); i++) {
			points[i*2]   = bound.get(i).x() + pos.x();
			points[i*2+1] = bound.get(i).y() + pos.y();
		}
		return new Polygon(points);
	}
	
	private boolean collides(Sprite s1, Sprite s2) {
		GamePoint pos1 = s1.getPos();
		CollisionBound bound1 = ((Collidable) s1.getCollidable().get()).getCollisionBound();
		Polygon polygon1 = getFXPolygon(bound1.getBoundPoints(), pos1);
		GamePoint pos2 = s2.getPos();
		CollisionBound bound2 = ((Collidable) s2.getCollidable().get()).getCollisionBound();
		Polygon polygon2 = getFXPolygon(bound2.getBoundPoints(), pos2);
		return polygon1.intersects(polygon2.getBoundsInLocal());
	}
	
	public void checkCollision(List<Sprite> sprites) {
		List<Sprite> collidableSprites = sprites.stream().filter((s) -> {
			return s.getCollidable().isPresent() &&
					s.getCollidable().get().isAttribute();
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
	
	public void checkBulletCollision(List<Bullet> bullets){
		List<Bullet> collidableBullets = bullets.stream().filter((s) -> {
			return s.getCollidable().isPresent() &&
					s.getCollidable().get().isAttribute() &&
					s.isMonster();
		}).collect(Collectors.toList());
		for (Bullet b1: collidableBullets) {
			if (collides(b1, b1.getTarget())){
				bus.emit(new DecrementHealthEvent(DecrementHealthEvent.ANY, b1, b1.getTarget()));
			}
		}
	}
	
	public void checkMonsterCollision(List<Sprite> sprites) {
		List<Sprite> collidableSprites = sprites.stream().filter((s) -> {
			return s.getCollidable().isPresent() &&
					s.getCollidable().get().isAttribute() &&
					s.isMonster();
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
