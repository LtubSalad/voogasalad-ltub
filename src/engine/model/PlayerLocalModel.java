package engine.model;

import bus.EventBus;
import engine.input.KeyInputState;
import engine.playerstate.PlayerSelectionState;
import engine.playerstate.PlayerSkillState;

/**
 * Contains data local to the player (user). Not seen by other players.
 * 
 * @author keping
 *
 */
public class PlayerLocalModel {

	private EventBus bus;
	private PlayerSelectionState selectionState;
	private PlayerSkillState skillState;

	public PlayerLocalModel(EventBus bus) {
		this.bus = bus;
	}

	private void initHandlers() {

	}

	public PlayerSelectionState getPlayerSelectionState() {
		return selectionState;
	}
	public void setPlayerSelectionState(PlayerSelectionState selectionState) {
		this.selectionState = selectionState;
	}

	public PlayerSkillState getPlayerSkillState() {
		return skillState;
	}
	public void setPlayerSkillState(PlayerSkillState skillState) {
		this.skillState = skillState;
	}

}
