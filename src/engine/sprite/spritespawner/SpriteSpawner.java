package engine.sprite.spritespawner;
import data.AttributeData;
import engine.sprite.Attribute;
import engine.sprite.Sprite; 


/**
 * Sprite spawner is an attribute which will most likely become a skill for a sprite. It is in charge of producing 
 * sprite if a sprite is capable of that
 * 
 * TODO convert to skill and improve so it has functionality
 */
public abstract class SpriteSpawner implements Attribute {
	private Sprite child;
	private double spawnTime; 
	
	/**
	 * Constructor which takes in AttributeData and currently does nothing with it
	 * @param data
	 * 
	 * TODO improve
	 */
	public SpriteSpawner(AttributeData data){
		
	}
	
	/**
	 * Constructor which takes in instance variables 
	 * @param childSprite Sprite which attribute is capable of reproducing
	 * @param time Time it takes between spawns
	 */
	public SpriteSpawner(Sprite childSprite, double time){
		this.child = childSprite;
		this.spawnTime = time; 
	}
	
	/**
	 * This method will do the actual spawning for the sprite based on instance variables
	 */
	public abstract void spawn();
	
}
