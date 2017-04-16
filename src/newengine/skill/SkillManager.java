package newengine.skill;

import bus.EventBus;
import engine.skill.events.ConfirmSkillEvent;

public class SkillManager {

	private EventBus bus;
	
	public SkillManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(ConfirmSkillEvent.CONFIRM, (e) -> {
			e.getSkill().trigger();
		});
	}
	
}
