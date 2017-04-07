package engine.playerstate;

import java.util.Optional;

import bus.EventBus;
import engine.skill.Skill;
import engine.skill.events.SelectSkillEvent;
import engine.sprite.LtubImage;

/**
 * Indicating the current selected skill.
 * Left click to confirm the skill.
 * @author keping
 *
 */
public class PlayerSkillState {

	private EventBus bus;
	private Skill skill;
	
	public PlayerSkillState(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(SelectSkillEvent.SELECT, (e) -> {
			setSelectedSkill(e.getSkill());
		});
		bus.on(SelectSkillEvent.DESELECT, (e) -> {
			setSelectedSkill(null);
		});
	}
	
	private void setSelectedSkill(Skill skill) {
		this.skill = skill;
	}
		
	public Optional<Skill> getSelectedSkill() {
		return Optional.ofNullable(skill);
	}
	
	public LtubImage getIcon() {
		// TODO return the icon of this skill
		return null;
	}

}
