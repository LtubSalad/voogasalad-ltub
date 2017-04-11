package engine.sprite.health;

import data.AttributeData;
import engine.sprite.Attribute;

public class HealthHolder implements Attribute {
	
	protected double currHealth;
	private Boolean hasHealth;
	
	public HealthHolder(AttributeData data){
		 this.currHealth = Double.parseDouble(data.getVariable("health"));
	}
	
	public HealthHolder(double initial){
		currHealth = initial;
		hasHealth = true;
	}
	
	public double getHealth(){
		return currHealth;
	}
	
	public void decrementHealth(double amt){
		currHealth = currHealth - amt;
	}
	
	//if health is 0 fire event that will remove this sprite from list of sprites

}
