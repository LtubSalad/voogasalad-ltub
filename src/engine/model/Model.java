package engine.model;

import java.util.List;

import engine.gameloop.LoopComponent;
import engine.playerstate.PlayerSelectionState;
import engine.sprite.Sprite;

public interface Model {

	public PlayerSelectionState getPlayerSelectionState();
	public void setPlayerSelectionState(PlayerSelectionState selectionState);
	
	public void update(double dt);
	
}
