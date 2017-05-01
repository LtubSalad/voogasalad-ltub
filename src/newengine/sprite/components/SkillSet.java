package newengine.sprite.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.events.skill.AddSkillEvent;
import newengine.events.skill.ResetSkillCooldownEvent;
import newengine.events.skill.TriggerSkillEvent;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SkillSet extends Component {

	public static final ComponentType<SkillSet> TYPE = new ComponentType<>(SkillSet.class.getName());
	private Map<SkillType<? extends Skill>, Skill> skills = new HashMap<>();
	
	@ConstructorForDeveloper
	public SkillSet(@VariableName(name = "Skills") Map<SkillType<? extends Skill>, Skill> skills) {
		this.skills = skills;
	}
	
	public List<Skill> skills() {
		return new ArrayList<Skill>(skills.values());
	}
	
	private void addSkill(Skill skill) {
		skills.put(skill.getType(), skill);
	}
	
	public Skill getSkill(SkillType<? extends Skill> type){
		return skills.get(type);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void afterAdded() {
		for (Skill skill: skills.values()) {
			skill.setSource(sprite);
		}
		sprite.on(AddSkillEvent.TYPE,  (e) -> {
			addSkill(e.getSkill());
			e.getSkill().setSource(sprite);
		});
		sprite.on(TriggerSkillEvent.ANY,  (e) -> {
			Skill skill = skills.get(e.getType());
			if (sprite.getComponent(Cooldown.TYPE).isPresent() &&
					!sprite.getComponent(Cooldown.TYPE).get().isReady(e.getType())) {
				return; // skill triggering is not executed if it is still in cooldown period.
			}
			if (skill != null) {
				skill.setSource(sprite);
				e.getTarget().ifPresent((target) -> skill.setTarget(target));
				skill.trigger();
			}
			sprite.emit(new ResetSkillCooldownEvent(e.getType()));
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

	@Override
	public Object[] getParameters() {
		Object[] parameters=new Object[1];
		parameters[0]=skills;
		return parameters;
	}

}
