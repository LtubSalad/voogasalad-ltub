package engine.sprite.health;

import engine.sprite.Attribute;

public class HealthHolder implements Attribute {
	
	protected double currHealth;
	private Boolean hasHealth;
	
	public HealthHolder(double initial){
		currHealth = initial;
		hasHealth = true;
	}

	@Override
	public void switchOn() {
		hasHealth = true;
	}

	@Override
	public void switchOff() {
		hasHealth = false;
	}

	@Override
	public Boolean isAttribute() {
		return hasHealth;
	}

	@Override
	public double update(double dt) {
		//not gonna do anything here because only changes when event is fired
		return 0;
	}
	
	public double getHealth(){
		return currHealth;
	}
	
	public void decrementHealth(double amt){
		currHealth = currHealth - amt;
	}
	
	//if health is 0 fire event that will remove this sprite from list of sprites

}
