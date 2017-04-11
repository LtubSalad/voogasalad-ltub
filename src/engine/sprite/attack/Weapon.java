package engine.sprite.attack;

import engine.camera.GamePoint;
import engine.sprite.Attribute;
import engine.sprite.Sprite;

public class Weapon implements Attribute {

	protected GamePoint currPos;
	protected GamePoint originPos;
	protected GamePoint targetPos;
	protected Sprite target;
	private Boolean isWeapon;
	private double damageDealt;

	public Weapon(Sprite o, Sprite t){
		originPos = o.getPos();
		if (t.getHealthHolder().isPresent()){
			target = t;
		}
		isWeapon = false;
	}

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

	//	public void setTarget(Sprite s){
	//		target = s;
	//	}

	public Sprite getTarget(){
		return target;
	}

	public double getDamageDealt(){
		return damageDealt;
	}

	@Override
	public double update(double dt) {
		// TODO Auto-generated method stub
		return 0;
	}


}
