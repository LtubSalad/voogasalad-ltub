package newengine.skill.skills;

import newengine.skill.Skill;
import newengine.skill.SkillType;

public class PlayerCreateSpriteSkill extends Skill {

	@Override
	public SkillType<? extends Skill> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		
	}

//	private EventBus bus;
//	private Sprite sprite;
//	private Target target;
//	private boolean isInstant = false;
//
//	public PlayerCreateSpriteSkill(EventBus bus, Sprite sprite) {
//		this.bus = bus;
//		this.sprite = sprite;
//	}
//
//	
//	@Override
//	public void setInstant(boolean instant) {
//		this.isInstant = instant;
//	}
//	/**
//	 * If instant, trigger directly when selected
//	 */
//	@Override
//	public boolean isInstant() {
//		return isInstant;
//	}
//
//	@Override
//	public void setTarget(Target target) {
//		this.target = target;
//	}
//	@Override
//	public Optional<Target> getTarget() {
//		return Optional.ofNullable(target);
//	}
//	@Override
//	public void trigger() {
//		// TODO: currently assume the target is a location
//		sprite.setPos(target.getLocation().get());
//		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, sprite));
//	}
//
//	@Override
//	public void setSource(Sprite sprite) {
//
//	}
//
//	@Override
//	public Optional<Sprite> getSource() {
//		return Optional.empty();
//	}



}
