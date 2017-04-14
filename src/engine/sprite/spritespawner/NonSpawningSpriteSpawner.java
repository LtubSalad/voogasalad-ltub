package engine.sprite.spritespawner;

import engine.sprite.Sprite;

/**
 * Concrete implementation of SpriteSpawner. This sprite spawner does not spawn
 * This class will not be necessary based on current design.
 * 
 * TODO make decision on this class
 */
public class NonSpawningSpriteSpawner extends SpriteSpawner {
	
	/**
	 * Constructor which takes in Sprite to spawn and time it takes to spawn
	 * @param childSprite
	 * @param time
	 */
	public NonSpawningSpriteSpawner(Sprite childSprite, double time){
		super(childSprite, time);
		
	}
	
	//public NonSpawningSpriteFactory(){
		//super(); 
	//}

	/**
	 * Does nothing based on type
	 */
	@Override
	public void spawn(){
		; 
		
	}

}
