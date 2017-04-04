package engine.model;

import engine.gameloop.LoopComponent;
import engine.sprite.Sprite;
import engine.view.View;

public interface Model {

	public void setView(View view);
	
	public void addSprite(Sprite sprite);
	
	public void removeSprite(Sprite sprite);
	
	public LoopComponent getLoopComponent();
	
}
