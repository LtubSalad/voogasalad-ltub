package engine.sprite.attack;

import java.util.ArrayList;
import java.util.List;

import engine.sprite.Attribute;
import engine.sprite.Sprite;
import engine.sprite.range.InRangeChecker;
import bus.EventBus;

public class Attacker implements Attribute {
	
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
	
	
	
}
