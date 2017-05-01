package newengine.sprite.components;

import java.util.HashMap;
import java.util.Map;

import helperAnnotations.ConstructorForDeveloper;
import newengine.events.skill.ResetSkillCooldownEvent;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Cooldown extends Component {

	public static final ComponentType<Cooldown> TYPE = new ComponentType<>(Cooldown.class.getName());

	private Map<SkillType<? extends Skill>, Double> remainingTime = new HashMap<>();
	
	@ConstructorForDeveloper
	public Cooldown(){
		
	}
	
	@Override
	public void afterAdded() {
		sprite.on(ResetSkillCooldownEvent.RESET, (e) -> {
			SkillType skillType = e.getSkillType();
			resetCooldown(skillType);
		});
	}

	@Override
	public void onUpdated(double dt) {
		for (SkillType skillType : remainingTime.keySet()) {
			if (remainingTime.get(skillType) >= 0) {
				remainingTime.put(skillType, remainingTime.get(skillType) - dt);
			}
		}
	}

	/**
	 * Returns whether the skill is ready. A skill is always ready if it is
	 * never triggered.
	 * 
	 * @param skillType
	 * @return
	 */
	public boolean isReady(SkillType skillType) {
		if (!remainingTime.containsKey(skillType)) {
			return true;
		}
		return remainingTime.get(skillType) < 0;
	}

	private void resetCooldown(SkillType skillType) {
		remainingTime.put(skillType, sprite.getComponent(SkillSet.TYPE).get().getSkill(skillType).getCooldown());
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Component clone() {
		Cooldown clone = new Cooldown();
		for (SkillType skillType : remainingTime.keySet()) {
			clone.remainingTime.put(skillType, remainingTime.get(skillType));
		}
		return clone;
	}

	@Override
	public Object[] getGUIParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
