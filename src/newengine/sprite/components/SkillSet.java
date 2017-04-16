package newengine.sprite.components;

import java.util.List;

import newengine.skill.Skill;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.variable.Var;

public class SkillSet extends Component {

	public static final ComponentType<SkillSet> TYPE = new ComponentType<>();
	private final Var<List<Skill>> skillsVar = new Var<>();
	
	public SkillSet(List<Skill> skills) {
		skillsVar.set(skills);
	}
	
	public List<Skill> skills() {
		return skillsVar.get();
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

}
