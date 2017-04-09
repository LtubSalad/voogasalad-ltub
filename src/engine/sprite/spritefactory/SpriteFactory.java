package engine.sprite.spritefactory;
import engine.sprite.Sprite; 

public abstract class SpriteFactory {
	protected Sprite child;
	protected double spawnTime; 
	public SpriteFactory(){
		child = null; 
		spawnTime = 0.0;
	}
	public SpriteFactory(Sprite childType, double time){
		this.child = childType;
		this.spawnTime = time; 
	}
	protected abstract void spawn();
	
}
