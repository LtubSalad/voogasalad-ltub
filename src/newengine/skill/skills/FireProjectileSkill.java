package newengine.skill.skills;

import newengine.events.sprite.FireProjectileEvent;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.sprite.Sprite;
import newengine.utils.Target;
import newengine.utils.image.LtubImage;

public class FireProjectileSkill extends Skill {

	public static final SkillType<FireProjectileSkill> TYPE = new SkillType<>(FireProjectileSkill.class.getName()); 

	public FireProjectileSkill() {
		icon = new LtubImage("images/skills/crosshairs.png");
	}

	@Override
	public void trigger() {
		if (canControl()) {
			Sprite source = this.getSource().get();
			Target target = this.getTarget().get();
			target.getSprite().ifPresent((targetSprite) -> {
				source.emit(new FireProjectileEvent(FireProjectileEvent.SPECIFIC, source, targetSprite));
			});
		}
	}

	@Override
	public SkillType<? extends Skill> getType() {
		return TYPE;
	}

}



//	private EventBus bus;
//	private boolean isInstant = false;
//	private Sprite source;
//	private Sprite projectile;
//	private Target target;
//	
//	public FireProjectileSkill(EventBus bus) {
//		this.bus = bus;
//		// TODO: currently a hard-coded projectile
//		Sprite sprite = new Sprite();
//		LtubImage image = new LtubImage("images/characters/bahamut_left.png");
//		ImageSet imageSet = new ImageSet();
//		imageSet.setImage(image);
//		sprite.setImageSet(imageSet);
//		//Movable movable = new Movable(sprite);
//		//sprite.setMovable(movable);
//		sprite.setCollidable(new Collidable(new CollisionBound(image)));
//		sprite.setPlayer(Player.NATURE);
//		projectile = sprite;
//	}
//
//	@Override
//	public void setInstant(boolean isInstant) {
//		this.isInstant = isInstant;
//	}
//	@Override
//	public boolean isInstant() {
//		return isInstant;
//	}
//
//	@Override
//	public void setTarget(Target target) {
//		this.target = target;
//	}
//
//	@Override
//	public Optional<Target> getTarget() {
//		return Optional.ofNullable(target);
//	}
//
//	@Override
//	public void trigger() {
//		projectile.setPos(source.getPos());
//		projectile.executeAction(() -> {
//			// TODO
////			((Movable) projectile.getMovable().get()).moveTo(target);
//		});
//		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, projectile));
//	}
//
//	@Override
//	public void setSource(Sprite source) {
//		this.source = source;
//	}
//
//	@Override
//	public Optional<Sprite> getSource() {
//		return Optional.ofNullable(source);
//	}
//
//	


