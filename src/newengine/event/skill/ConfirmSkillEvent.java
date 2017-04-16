package newengine.event.skill;

import bus.BusEvent;
import bus.BusEventType;
import newengine.skill.Skill;

public class ConfirmSkillEvent extends BusEvent {

	public static final BusEventType<ConfirmSkillEvent> CONFIRM = new BusEventType<>("CONFIRM_SKILL");
	
	private Skill skill;
	
	public ConfirmSkillEvent(BusEventType<? extends BusEvent> busEventType, Skill skill) {
		super(busEventType);
		this.skill = skill;
	}
	
	public Skill getSkill() {
		return skill;
	}
	
}
