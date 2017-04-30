package newengine.skill;

import java.util.Optional;

import newengine.model.PlayerRelationModel;
import newengine.skill.skills.BuildSkill;
import newengine.sprite.Sprite;
import newengine.utils.Target;
import newengine.utils.image.LtubImage;

public abstract class Skill {
	
	protected LtubImage icon;
	protected Sprite source = new Sprite();
	protected Target target;
	
	public void setSource(Sprite source) {
		this.source = source;
	}
	public Optional<Sprite> getSource() {
		return Optional.ofNullable(source);
	}
	public void setTarget(Target target) {
		this.target = target;
	}
	public Optional<Target> getTarget() {
		return Optional.ofNullable(target);
	}
	public Optional<LtubImage> getIcon() {
		return Optional.ofNullable(icon);
	}
	public abstract void trigger();
	public abstract SkillType<? extends Skill> getType();
	
	/**
	 * Return the cooldown time of the skill. Negative number
	 * means no cooldown time.
	 * @return
	 */
	public double getCooldown() {
		return -1;
	}

}
