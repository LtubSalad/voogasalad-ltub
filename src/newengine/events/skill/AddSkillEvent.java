package newengine.events.skill;

import bus.BusEvent;
import bus.BusEventType;
import newengine.skill.Skill;

public class AddSkillEvent extends BusEvent{
	
	public static final BusEventType<AddSkillEvent> TYPE = new BusEventType<>(AddSkillEvent.class.getName());

	private Skill skill;
	
	public AddSkillEvent(BusEventType<? extends BusEvent> busEventType, Skill skill) {
		super(busEventType);
		this.skill = skill;
	}
	
	public Skill getSkill() {
		return skill;
	}

}
