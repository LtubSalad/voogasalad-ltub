package engine.sprite.attack;


import engine.sprite.Attribute;
import bus.EventBus;
import data.AttributeData;

public class Attacker implements Attribute {
	private double radius;
	
	public Attacker(AttributeData data){
		this.radius = Double.parseDouble(data.getVariable("radius"));
	}
	
	public Attacker(){
		radius = 128;

	}
	
	public double getRange(){
		return this.radius;
	}

	public void setRange(double detectionRange) {
		this.radius = detectionRange;
	}
	
}
