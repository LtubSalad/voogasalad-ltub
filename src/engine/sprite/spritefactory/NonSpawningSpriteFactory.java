package engine.sprite.spritefactory;

import engine.sprite.Sprite;

public class NonSpawningSpriteFactory extends SpriteFactory {
	
	public NonSpawningSpriteFactory(Sprite childSprite, double time){
		super(childSprite, time);
		
	}
	
	public NonSpawningSpriteFactory(){
		super(); 
	}

	@Override
	protected void spawn(){
		; 
		
	}

}
