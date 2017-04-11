package engine.sprite.spritespawner;
import data.AttributeData;
import engine.sprite.Sprite; 

public abstract class SpriteSpawner {
	private Sprite child;
	private double spawnTime; 
	
	public SpriteSpawner(AttributeData data){
		
	}
	public SpriteSpawner(Sprite childType, double time){
		this.child = childType;
		this.spawnTime = time; 
	}
	protected abstract void spawn();
	
}
