package engine.sprite;

import engine.sprite.collision.Collidable;
import engine.sprite.spritespawner.SpriteSpawner;

/**
 * @author tahiaemran 
 *
 *interface for defining methods used to create sprites or change their attributes 
 */
public interface SpriteCreator {
	public void setMovable(Movable movable);
	public void setCollidable(Collidable collidable); 
	//public void setAttacker(); 
	//public void setHealth(); 
	public void setFactory(SpriteSpawner factory); 
	//public void setWeapon(); 
}
