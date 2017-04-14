package engine.sprite.nodeholder;

import data.AttributeData;
import engine.sprite.Attribute;
import engine.sprite.images.LtubImage;
import javafx.scene.Node;


/**
 * Attribute class for the sprite which contains information on its visual representation
 *
 * TODO adapt sprite to work based on this instead of just imageSet / make a decision on that
 */
public class NodeHolder implements Attribute{
	private Node myNode; 
	private AttributeData myData;
	
	/**
	 * Constructor which takes in an AttributeData class, currently does nothing with it but stores as instance
	 * @param data AttributeData
	 * 
	 * TODO make non-AttributeData constructor
	 */
	public NodeHolder(AttributeData data){
		myData = data; 
	}
}
