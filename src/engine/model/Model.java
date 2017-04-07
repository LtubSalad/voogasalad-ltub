package engine.model;

import java.util.List;

import engine.playerstate.PlayerSelectionState;
import engine.playerstate.PlayerSkillState;
import engine.sprite.Sprite;

public interface Model {

	public void addSprite(Sprite sprite);
	
	public void removeSprite(Sprite sprite);
	
	public List<Sprite> getSprites();
	
	public PlayerSelectionState getPlayerSelectionState();
	public void setPlayerSelectionState(PlayerSelectionState selectionState);
	
	public PlayerSkillState getPlayerSkillState();
	public void setPlayerSkillState(PlayerSkillState skillState);
	
	public void update(double dt);
	
}
