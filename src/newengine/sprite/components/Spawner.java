package newengine.sprite.components;

import java.util.HashMap;
import java.util.Map;

import commons.point.GamePoint;
import newengine.events.SpriteModelEvent;
import newengine.events.sprite.FireProjectileEvent;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.skill.skills.MoveSkill;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.sprite.components.Collidable.CollisionBoundType;
import newengine.sprite.components.Selectable.SelectionBoundType;
import newengine.sprite.player.Player;
import newengine.utils.image.ImageSet;
import newengine.utils.image.LtubImage;

public class Spawner extends Component {

	public static final ComponentType<Position> TYPE = new ComponentType<>(Position.class.getName());

	public Spawner() {

	}

	@Override
	public void afterAdded() {
		sprite.on(FireProjectileEvent.SPECIFIC, e -> {
			System.out.println("emit FireProjectileEvent");
			Player weapons = new Player("weapons");

			Sprite weapon = new Sprite();
			LtubImage image1 = new LtubImage("images/characters/crosshairs.png");
			ImageSet imageSet1 = new ImageSet(image1);
			Map<SkillType<? extends Skill>, Skill> skillMap = new HashMap<>();
			MoveSkill moveSkill = new MoveSkill();
			skillMap.put(MoveSkill.TYPE, moveSkill);
			weapon.addComponent(new SkillSet(skillMap));
			weapon.addComponent(new Owner(weapons));
			weapon.addComponent(new Position(sprite.getComponent(Position.TYPE).get().pos(), sprite.getComponent(Position.TYPE).get().heading()));
			weapon.addComponent(new Images(imageSet1));
			weapon.addComponent(new Speed(200));
			weapon.addComponent(new Collidable(CollisionBoundType.IMAGE));
			
			sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(new SpriteModelEvent(SpriteModelEvent.ADD, weapon));


			System.out.println("created weapon for " + e.getTarget().getSprite().get().getID());
			if (e.getTarget().getSprite().isPresent()) {
				moveSkill.setTarget(e.getTarget());
				moveSkill.trigger();
			}
		});
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}



}
