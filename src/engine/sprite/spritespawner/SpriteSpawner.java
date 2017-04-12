package engine.sprite.spritespawner;
import data.AttributeData;
import engine.sprite.Attribute;
import engine.sprite.Sprite; 

public abstract class SpriteSpawner implements Attribute {
	private Sprite child;
	private double spawnTime; 
	
	public SpriteSpawner(AttributeData data){
		
	}
	public SpriteSpawner(Sprite childSprite, double time){
		this.child = childSprite;
		this.spawnTime = time; 
	}
	
	public abstract void spawn();
	
}
