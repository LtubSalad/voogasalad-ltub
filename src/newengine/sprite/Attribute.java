package newengine.sprite;

/**
 * Attribute stores mutable data for the sprite.
 * 
 * @author keping
 *
 */
public abstract class Attribute {

	public abstract <T extends Attribute> AttributeType<T> getType();
	
}
