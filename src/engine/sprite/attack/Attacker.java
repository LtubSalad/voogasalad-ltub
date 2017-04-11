package engine.sprite.attack;


import engine.sprite.Attribute;
import bus.EventBus;
import data.AttributeData;

public class Attacker implements Attribute {
	private double radius;
	private int damage;
	
	public Attacker(AttributeData data){
		this.radius = Double.parseDouble(data.getVariable("radius"));
		this.damage = Integer.parseInt(data.getVariable("damage"));
	}
	
	private Boolean isAttacker;
	
	
	public Attacker(EventBus eb){
		isAttacker = false;
	}
	

	@Override
	public void switchOn() {
		isAttacker = true;
	}

	@Override
	public void switchOff() {
		isAttacker = false;
	}

	@Override
	public Boolean isAttribute() {
		return isAttacker;
	}

	@Override
	public double update(double dt) {
		
		return 0.0;
	}
	
	public double getRange(){
		return this.radius;
	}


	public void setRange(double detectionRange) {
		this.radius = detectionRange;
	}
	
}
