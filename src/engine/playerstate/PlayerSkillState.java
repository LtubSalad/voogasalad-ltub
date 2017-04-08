package engine.playerstate;

import java.util.Optional;

import bus.EventBus;
import engine.input.events.GameWorldMouseEvent;
import engine.skill.Skill;
import engine.skill.events.ConfirmSkillEvent;
import engine.skill.events.SelectSkillEvent;
import engine.sprite.images.LtubImage;

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
