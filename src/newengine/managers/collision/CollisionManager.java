package newengine.managers.collision;

import java.util.List;
import java.util.stream.Collectors;

import bus.EventBus;
import imageprocess.Collision;
import newengine.events.collision.CollisionEvent;
import newengine.sprite.Sprite;
import newengine.sprite.components.Collidable;
import newengine.utils.checker.CollisionChecker;

public class CollisionManager {

	private EventBus bus;

	public CollisionManager(EventBus bus) {
		this.bus = bus;
	}

	public void checkCollisions(List<Sprite> sprites) {
		List<Sprite> collidableSprites = sprites.stream().filter((s) -> {
			return s.getComponent(Collidable.TYPE).isPresent();
		}).collect(Collectors.toList());

		for (Sprite s1 : collidableSprites) {
			for (Sprite s2 : collidableSprites) {
				if (s1 == s2) { continue; }
				
				//TODO: can change the if statement as the following condtion
				//This will use the convex hull of the image to check collision
				//if (Collision.isCollided(s1, s2)
				if (CollisionChecker.collides(s1, s2)) {
					// both sprites will receive this collision event. 
					s1.emit(new CollisionEvent(CollisionEvent.ANY, s1, s2));
					s2.emit(new CollisionEvent(CollisionEvent.ANY, s2, s1));
				}
			}
		}
	}

}
