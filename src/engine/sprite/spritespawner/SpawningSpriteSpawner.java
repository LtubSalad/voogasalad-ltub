package engine.sprite.spritespawner;

import engine.sprite.Sprite;

/**
 * Concrete implementation of SpriteSpawner. Will most likely not be necessary based on current design
 * TODO make decision on this class
 */
public class SpawningSpriteSpawner extends SpriteSpawner {
	/**
	 * Constructor which takes in sprite to reproduce and time it takes to spawn
	 * @param childSprite
	 * @param time
	 */
	public SpawningSpriteSpawner(Sprite childSprite, double time){
		super(childSprite, time);
	}

	/**
	 * This method spawns the sprite, not implemented currently
	 */
	@Override
	public void spawn() {
		// TODO Auto-generated method stub
		// for every time interval, create a new sprite, add it to the screen etc.  
		
	}

}
