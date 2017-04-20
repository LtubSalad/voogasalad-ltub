package newengine.managers.collision;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bus.EventBus;
import newengine.events.SpriteModelEvent;
import newengine.events.collision.CollisionEvent;
import newengine.events.sprite.ChangeHealthEvent;
import newengine.events.sprite.WeaponCollisionEvent;
import newengine.skill.skills.MoveSkill;
import newengine.sprite.Sprite;
import newengine.sprite.components.Collidable;
import newengine.sprite.components.DamageStrength;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Health;
import newengine.sprite.components.Position;
import newengine.sprite.components.SkillSet;
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
				if (CollisionChecker.collides(s1, s2)) {
					// NOTE: both sprites will receive this collision event. 
					System.out.println("collision");
					s1.emit(new CollisionEvent(CollisionEvent.ANY, s1, s2));
					s2.emit(new CollisionEvent(CollisionEvent.ANY, s2, s1));
				}
			}
		}
	}

	public void checkWeaponCollisions(List<Sprite> sprites) {
		List<Sprite> collidableWeaponSprites = sprites.stream().filter((s) -> {
			return (s.getComponent(Collidable.TYPE).isPresent() && s.getComponent(DamageStrength.TYPE).isPresent()
					&& s.getComponent(Position.TYPE).isPresent()
					&& s.getComponent(SkillSet.TYPE).get().getSkill(MoveSkill.TYPE).getTarget().get().getSprite().isPresent());
		}).collect(Collectors.toList());
		for (Sprite weapon : collidableWeaponSprites) {
			Sprite target = weapon.getComponent(SkillSet.TYPE).get().getSkill(MoveSkill.TYPE).getTarget().get().getSprite().get();
			if (!weapon.getComponent(Position.TYPE).get().isMoving()){
				List<Sprite> remove = new ArrayList<Sprite>();
				remove.add(weapon);
				bus.emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, remove));
			}
			if (CollisionChecker.collidesOnTarget(weapon, target)) {
				System.out.println("weapon strength: " + weapon.getComponent(DamageStrength.TYPE).get().getStrength());
				System.out.println("health before hit: " + target.getComponent(Health.TYPE).get().getHealth());
				target.emit(new ChangeHealthEvent(ChangeHealthEvent.ANY, weapon.getComponent(DamageStrength.TYPE).get().getStrength()));
				System.out.println("health after hit: " + target.getComponent(Health.TYPE).get().getHealth());
			}
		}
	}


}
