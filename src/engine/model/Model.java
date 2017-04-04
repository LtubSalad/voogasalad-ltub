package engine.model;

import java.util.List;

import engine.gameloop.LoopComponent;
import engine.sprite.Sprite;

public interface Model {

	public void addSprite(Sprite sprite);
	
	public void removeSprite(Sprite sprite);
	
	public List<Sprite> getSprites();
	
	public LoopComponent getLoopComponent();
	
}
