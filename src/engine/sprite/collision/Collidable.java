package engine.sprite.collision;

import data.AttributeData;
import engine.sprite.Attribute;
import engine.sprite.images.LtubImage;

public class Collidable implements Attribute{

	private CollisionBound bound;
	private Boolean isCollidable;
	
	public Collidable(AttributeData data){
		this.isCollidable = Boolean.parseBoolean(data.getVariable("isCollidable"));
		this.bound = new CollisionBound(new LtubImage(data.getVariable("imagePath")));
	}
	
	public Collidable(CollisionBound bound) {
		this.bound = bound;
		isCollidable = true;
	}
	
	public CollisionBound getCollisionBound() {
		return bound;
	}
	
}
