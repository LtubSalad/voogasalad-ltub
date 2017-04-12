package engine.sprite.spritespawner;

import engine.sprite.Sprite;

public class NonSpawningSpriteSpawner extends SpriteSpawner {
	
	public NonSpawningSpriteSpawner(Sprite childSprite, double time){
		super(childSprite, time);
		
	}
	
	//public NonSpawningSpriteFactory(){
		//super(); 
	//}

	@Override
	public void spawn(){
		; 
		
	}

}
