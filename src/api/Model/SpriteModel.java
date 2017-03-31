package api.Model;

import java.util.Collection;

public interface SpriteModel {
	/**
	 * @return a read-only collection of the sprites in the model
	 */
	public Collection<Object> getReadOnlySprites(); 
}
