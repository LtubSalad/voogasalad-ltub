package engine.model;

import java.util.List;

import engine.sprite.Sprite;

public interface ISpriteHandler {
	public void addSprite(Sprite s);
	public void removeSprite(Sprite s);
	public List<Sprite> getSprites(); 

}
