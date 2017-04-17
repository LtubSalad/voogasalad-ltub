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
		Sprite source = this.getSource().get();
		Target target = this.getTarget().get();
		source.emit(new MoveEvent(MoveEvent.SPECIFIC, source, target));
	}
	
	@Override
	public SkillType<? extends Skill> getType() {
		return TYPE;
	}
}
