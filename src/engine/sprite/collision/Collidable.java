package engine.sprite.collision;

import data.AttributeData;
import engine.sprite.Attribute;
import engine.sprite.images.LtubImage;

public class Collidable implements Attribute{

	private CollisionBound bound;
	
	public Collidable(AttributeData data){
		this.bound = new CollisionBound(new LtubImage(data.getVariable("imagePath")));
	}
	
	public Collidable(CollisionBound bound) {
		this.bound = bound;
	}
	
	public CollisionBound getCollisionBound() {
		return bound;
	}
	
}
