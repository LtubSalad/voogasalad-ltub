package engine.model;

import java.util.List;

import engine.gameloop.LoopComponent;
import engine.input.PlayerSelectionState;
import engine.sprite.Sprite;

public interface Model {

	public void addSprite(Sprite sprite);
	
	public void removeSprite(Sprite sprite);
	
	public List<Sprite> getSprites();
	
	public PlayerSelectionState getPlayerSelectionState();
	public void setPlayerSelectionState(PlayerSelectionState selectionState);
	
	public LoopComponent getLoopComponent();
	
}
