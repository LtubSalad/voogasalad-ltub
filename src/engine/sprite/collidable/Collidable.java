package engine.sprite.collidable;

import data.AttributeData;
import engine.sprite.Attribute;
import engine.sprite.images.LtubImage;

public class Collidable implements Attribute{

	private CollisionBound bound;
	
//	public Collidable(AttributeData data){
//		this(data.getVariable("imagePath"));
//	}
	
	public Collidable(CollisionBound bound) {
		this.bound = bound;
	}
	
	public Collidable(String imagePath) {
		this.bound = new CollisionBound(new LtubImage("images/characters/" + imagePath));
	}

	public CollisionBound getCollisionBound() {
		return bound;
	}
	
}
