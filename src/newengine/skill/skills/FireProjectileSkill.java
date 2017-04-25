package newengine.skill.skills;

import newengine.events.sprite.FireProjectileEvent;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.sprite.Sprite;
import newengine.utils.Target;
import newengine.utils.image.LtubImage;

public class FireProjectileSkill extends Skill {

	public static final SkillType<FireProjectileSkill> TYPE = new SkillType<>(FireProjectileSkill.class.getName()); 
	private double cooldown = 1;

	public FireProjectileSkill() {
		icon = new LtubImage("images/skills/crosshairs.png");
	}

	public FireProjectileSkill(double cooldown){
		this.cooldown = cooldown;
	}

	public void setCooldown(double cooldown) { // TODO: it's better if there is no setter API? 
		this.cooldown = cooldown;
	}
	
	@Override
	public double getCooldown() {
		return cooldown;
	}

	@Override
	public void trigger() {
		Sprite source = this.getSource().get();
		Target target = this.getTarget().get();
		target.getSprite().ifPresent((targetSprite) -> {
			source.emit(new FireProjectileEvent(FireProjectileEvent.SPECIFIC, source, targetSprite));
		});
	}

	@Override
	public SkillType<? extends Skill> getType() {
		return TYPE;
	}

}

