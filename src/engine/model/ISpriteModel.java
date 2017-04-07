package engine.model;

import java.util.Collection;


public interface ISpriteModel<T> {
	public void addSprite(T sprite);
	
	public void removeSprite(T sprite);
	
	public Collection<T> getSprites();
}
