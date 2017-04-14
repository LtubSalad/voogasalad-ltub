package engine.sprite;

import engine.sprite.collidable.Collidable;
import engine.sprite.movable.Movable;
import engine.sprite.spritespawner.SpriteSpawner;

/**
 * @author Tahia Emran 
 *interface for defining methods used to create sprites or change their attributes. This will in theory be implemented
 *by sprite so as to enforce the necessary methods needed during sprite creation on the object
 *
 * TODO Add the necessary interface methods after the code freeze
 */
public interface SpriteCreator {
	/**
	 * Sets the movable attribute of the sprite
	 * @param movable
	 */
	public void setMovable(Movable movable);
	
	/**
	 * Sets the collidable attribute of the sprite
	 * @param collidable
	 */
	public void setCollidable(Collidable collidable); 
	
	//public void setAttacker(); 
	//public void setHealth(); 
	
	/**
	 * Sets the SpriteSpawner attribute of the sprite 
	 * @param factory
	 */
	public void setFactory(SpriteSpawner factory); 
	
	//public void setWeapon(); 
}
