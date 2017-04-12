package engine.sprite.weapon;

import engine.camera.GamePoint;
import engine.sprite.Attribute;
import engine.sprite.Sprite;

public class Weapon implements Attribute {

	protected GamePoint currPos;
	protected GamePoint originPos;
	protected GamePoint targetPos;
	protected Sprite target;
	private Boolean isWeapon;
<<<<<<< HEAD
	private double attackPower;
=======
	private double damageDealt;
	
>>>>>>> 958501f4351c517f30bf40061795e52b1dac9551

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

	//	public void setTarget(Sprite s){
	//		target = s;
	//	}

	public Sprite getTarget(){
		return target;
	}

	public void setAttackPower(double ap){
		attackPower = ap;
	}
	
	public double getAttackPower(){
		return attackPower;
	}

}
