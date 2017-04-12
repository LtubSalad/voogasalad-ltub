package engine.sprite.attacker;


import engine.sprite.Attribute;
import bus.EventBus;
import data.AttributeData;

public class Attacker implements Attribute {
	private double radius;
	private int damage;
	
//	public Attacker(AttributeData data){
//		this.radius = Double.parseDouble(data.getVariable("radius"));
//		this.damage = Integer.parseInt(data.getVariable("damage"));
//	}
	
	public Attacker(double radius, int damage){
		this.radius = radius;
		this.damage = damage;
	}
	
	public double getRange(){
		return this.radius;
	}

	public void setRange(double detectionRange) {
		this.radius = detectionRange;
	}
	
}
