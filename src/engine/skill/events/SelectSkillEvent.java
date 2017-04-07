package engine.skill.events;

import bus.BusEvent;
import bus.BusEventType;
import engine.skill.Skill;

public class SelectSkillEvent extends BusEvent {

	public static final BusEventType<SelectSkillEvent> SELECT = new BusEventType<>("SELECT_SKILL");
	public static final BusEventType<SelectSkillEvent> CANCEL = new BusEventType<>("DESELECT_SKILL");
	
	private Skill skill;
	
	public SelectSkillEvent(BusEventType<? extends BusEvent> busEventType, Skill skill) {
		super(busEventType);
		this.skill = skill;
	}
	
	public Skill getSkill() {
		return skill;
	}
	
}
