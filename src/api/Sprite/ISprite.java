package api.Sprite;

/**
 * Public methods for the Sprite which are needed to ensure their proper behavior
 * and dynamic changeability in the game play.
 * @author Matthew Tribby
 */
public interface ISprite {

	/**
	 * Sets the attribute of the sprite. 
	 * @param attributeType Name of the attribute type which will allow for
	 * 						changing the behavior of the sprite through changing
	 * 						the attribute of the sprite
	 */
	public void setAttribute(String attributeType);

	public void onAttacked(Object e);
	
}
