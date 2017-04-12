package engine.sprite.healthholder;

import data.AttributeData;
import engine.sprite.Attribute;

public class HealthHolder implements Attribute {
	
	private double currHealth;
	
	public HealthHolder(AttributeData data){
		 this.currHealth = Double.parseDouble(data.getVariable("health"));
	}
	
	public HealthHolder(double initial){
		currHealth = initial;
	}
	
	public double getHealth(){
		return currHealth;
	}
	
	public void decrementHealth(double amt){
		currHealth = currHealth - amt;
	}
}
