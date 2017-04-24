package newengine.events.skill;

import bus.BusEvent;
import bus.BusEventType;
import newengine.skill.SkillType;

public class ResetSkillCooldownEvent extends BusEvent {
	public static final BusEventType<ResetSkillCooldownEvent> RESET = new BusEventType<>(
			ResetSkillCooldownEvent.class.getName() + "RESET");
	
	private SkillType skillType;
	
	public ResetSkillCooldownEvent(SkillType skillType) {
		super(RESET);
		this.skillType = skillType;
	}
	
	public SkillType getSkillType() {
		return skillType;
	}

}
