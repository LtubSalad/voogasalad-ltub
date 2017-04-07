package engine.action;

import bus.EventBus;
import engine.skill.events.ConfirmSkillEvent;
import engine.skill.events.SelectSkillEvent;

public class PlayerActionManager {

	private EventBus bus;
	
	public PlayerActionManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(SelectSkillEvent.SELECT, (e) -> {
			if (e.getSkill().isInstant()) {
				bus.emit(new ConfirmSkillEvent(ConfirmSkillEvent.CONFIRM, e.getSkill()));
				bus.emit(new SelectSkillEvent(SelectSkillEvent.DESELECT, e.getSkill()));
			}
		});
	}
}
