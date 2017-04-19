package newengine.sprite.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import newengine.events.skill.AddSkillEvent;
import newengine.events.skill.TriggerSkillEvent;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SkillSet extends Component {

	public static final ComponentType<SkillSet> TYPE = new ComponentType<>(SkillSet.class.getName());
	private Map<SkillType<? extends Skill>, Skill> skills = new HashMap<>();
	
	public SkillSet(Map<SkillType<? extends Skill>, Skill> skills) {
		this.skills = skills;
	}
	
	public List<Skill> skills() {
		return new ArrayList<Skill>(skills.values());
	}
	
	private void addSkill(Skill skill) {
		skills.put(skill.getType(), skill);
	}
	
	@Override
	public void afterAdded() {
		for (Skill skill: skills.values()) {
			skill.setSource(sprite);
		}
		sprite.on(AddSkillEvent.TYPE, (e) -> {
			addSkill(e.getSkill());
			e.getSkill().setSource(sprite);
		});
		sprite.on(TriggerSkillEvent.ANY, (e) -> {
			Skill skill = skills.get(e.getType());
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
	
	@Override
	public SkillSet clone() {
		return new SkillSet(skills);
	}

}
