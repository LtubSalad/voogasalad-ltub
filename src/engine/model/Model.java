package engine.model;

import engine.sprite.KepingSprite;
import engine.view.View;

public interface Model {

	public void setView(View view);
	
	public void addSprite(KepingSprite sprite);
	
	public void removeSprite(KepingSprite sprite);
	
}
