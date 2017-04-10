package engine.sprite.attack;

import engine.camera.GamePoint;
import engine.sprite.Attribute;
import engine.sprite.Sprite;

public abstract class Weapon extends Sprite implements Attribute {

	protected GamePoint currPos;
	protected GamePoint originPos;
	protected GamePoint targetPos;
	protected Sprite target;
	private Boolean isWeapon;

	public Weapon(Sprite o, Sprite t){
		originPos = o.getPos();
		target = t;
		isWeapon = false;
	}

	public double update(double dt){
		updateTargetPos();
		updateWeaponPos();
		checkIfHit();
		decrementMonsterHealth();
		return dt;
	}
	
	private void updateTargetPos(){
		targetPos = target.getPos();
	}

	public abstract void updateWeaponPos();
	
	public abstract void checkIfHit();
	
	public abstract void decrementMonsterHealth();

	public GamePoint getThisPos(){
		return currPos;
	}

	@Override
	public void switchOn() {
		isWeapon = true;
	}

	@Override
	public void switchOff() {
		isWeapon = false;
	}

	@Override
	public Boolean isAttribute() {
		return isWeapon;
	}
	
	public Sprite getTarget(){
		return target;
	}



}
