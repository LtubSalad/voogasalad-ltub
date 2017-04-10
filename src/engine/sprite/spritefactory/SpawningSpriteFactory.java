package engine.sprite.spritefactory;

import engine.sprite.Sprite;

public class SpawningSpriteFactory extends SpriteFactory {
	public SpawningSpriteFactory(Sprite childSprite, double time){
		super(childSprite, time);
	}

	@Override
	protected void spawn() {
		// TODO Auto-generated method stub
		// for every time interval, create a new sprite, add it to the screen etc.  
		
	}

}
