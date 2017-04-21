package newengine.skill.skills;

import newengine.events.sprite.MoveEvent;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.sprite.Sprite;
import newengine.utils.Target;
import newengine.utils.image.LtubImage;

public class MoveSkill extends Skill{
	
	public static final SkillType<MoveSkill> TYPE = new SkillType<>(MoveSkill.class.getName());
	
	public MoveSkill() {
		icon = new LtubImage("images/skills/walk.png");
	}
	
	@Override
	public void trigger() {
		if (canControl()) {
			Sprite source = this.getSource().get();
			Target target = this.getTarget().get();
			if (target.getSprite().isPresent()) {
				source.emit(new MoveEvent(MoveEvent.START_SPRITE, source, target));
			}
			else {
				source.emit(new MoveEvent(MoveEvent.START_POSITION, source, target));
			}	
		}
	}
	
	@Override
	public SkillType<? extends Skill> getType() {
		return TYPE;
	}
}
