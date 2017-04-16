package newengine.model;

import java.util.Optional;

import bus.EventBus;
import newengine.event.input.GameWorldMouseEvent;
import newengine.event.skill.ConfirmSkillEvent;
import newengine.event.skill.SelectSkillEvent;
import newengine.skill.Skill;
import newengine.utils.image.LtubImage;

/**
 * Indicating the current selected skill. Left click to confirm the skill.
 * 
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
		bus.on(SelectSkillEvent.CANCEL, (e) -> {
			if (skill != null) {
				setSelectedSkill(null);
			}
		});
		bus.on(GameWorldMouseEvent.CONFIRM_SKILL, (e) -> {
			if (skill != null) {
				skill.setTarget(e.getTarget());
				bus.emit(new ConfirmSkillEvent(ConfirmSkillEvent.CONFIRM, skill));
				setSelectedSkill(null);
			} else {
				bus.emit(new GameWorldMouseEvent(GameWorldMouseEvent.SELECT_SPRITE, e.getTarget(), e.getActionMode(), e.getPlayer()));
			}
		});
		bus.on(GameWorldMouseEvent.CANCEL_SKILL_AND_MOVE_SPRITE, (e) -> {
			if (skill != null) {
				setSelectedSkill(null);				
			}
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
