package engine.model;

import bus.EventBus;
import engine.playerstate.PlayerInputState;
import engine.playerstate.PlayerSelectionState;
import engine.playerstate.PlayerSkillState;

/**
 * Contains data local to the player (user).
 * @author keping
 *
 */
public class PlayerLocalModel {
	
	private EventBus bus;
	private PlayerInputState inputState;
	private PlayerSelectionState selectionState;
	private PlayerSkillState skillState;
	
	public PlayerLocalModel(EventBus bus) {
		this.bus = bus;
	}
	
	private void initHandlers() {
		
	}
	
	public PlayerInputState getPlayerInputState() {
		return inputState;
	}
	public void setPlayerInputState(PlayerInputState inputState) {
		this.inputState = inputState;
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
