package api.ScreenModel;

import java.util.Collection;

public interface ScreenModel {
	/**
	 * @return a collection of read only sprites 
	 */
	public Collection<Object> getReadOnlySprites(); 
	/**
	 * @param gameObject used to add an object to the game 
	 */
	public void addGameObject(Object gameObject);
	/**
	 * @return a collection of the monsters in the game
	 */
	public Collection<Object> getMonsters(); 
}
