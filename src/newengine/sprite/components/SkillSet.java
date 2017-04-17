package newengine.sprite.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import newengine.events.skill.TriggerSkillEvent;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.variable.Var;

public class SkillSet extends Component {

	public static final ComponentType<SkillSet> TYPE = new ComponentType<>(SkillSet.class.getName());
	private final Var<Map<SkillType<? extends Skill>, Skill>> skillsVar = new Var<>();
	
	public SkillSet(Map<SkillType<? extends Skill>, Skill> skills) {
		skillsVar.set(skills);
	}
	
	public List<Skill> skills() {
		return new ArrayList<Skill>(skillsVar.get().values());
	}
	
	@Override
	public void afterAdded() {
		for (Skill skill: skillsVar.get().values()) {
			skill.setSource(sprite);
		}
		sprite.on(TriggerSkillEvent.ANY, (e) -> {
			Map<SkillType<? extends Skill>, Skill> map = skillsVar.get();
			Skill skill = map.get(e.getType());
			if (skill != null) {
				e.getTarget().ifPresent((e1) -> skill.setTarget(e.getTarget().get()));
				skill.trigger();
			}
		});
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

}
