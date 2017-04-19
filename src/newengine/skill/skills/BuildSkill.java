package newengine.skill.skills;

import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.sprite.Sprite;
import newengine.sprite.components.Images;
import newengine.sprite.components.Position;
import newengine.utils.Target;

public class BuildSkill extends Skill{
	
	public static final SkillType<BuildSkill> TYPE = new SkillType<>(BuildSkill.class.getName());
	private Sprite spriteToCreate;
	
	public BuildSkill(Sprite sprite) {
		this.spriteToCreate = sprite;
		if (sprite.getComponent(Images.TYPE).isPresent()) {
			this.icon = sprite.getComponent(Images.TYPE).get().image();
		}
	}
	
	@Override
	public void trigger() {
		Target target = this.getTarget().get();
		spriteToCreate.addComponent(new Position(target.getLocation(), 0));
		
	}

	@Override
	public SkillType<? extends Skill> getType() {
		return TYPE;
	}

	
}
