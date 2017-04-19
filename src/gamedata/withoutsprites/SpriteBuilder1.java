package gamedata.withoutsprites;

/**
 * @author tahiaemran
 * 
 * interface used for sprite creation if data passed is an XStream of a collection of components/ skills that make 
 * up a sprite, not the Sprite Object itself  
 *
 */
public interface SpriteBuilder1 {
	/**
	 * method used to define the components of the sprite based on what is specified in the data file
	 */
	public void defineComponents();
	/**
	 * method used to define the skills of the sprite based on what is specified in the data file
	 */
	public void defineSkills(); 
}
