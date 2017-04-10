package engine.sprite.attack;

import engine.camera.GamePoint;
import engine.sprite.Sprite;

public class Bullet extends Weapon {
	
	private double damageDealt;
	

	public Bullet(Sprite o, Sprite t) {
		super(o,t);
		damageDealt = 10;
		currPos = this.getPos();
	}

	@Override
	public void updateWeaponPos() {
		
	}

	@Override
	public void checkIfHit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrementMonsterHealth() {
		// TODO Auto-generated method stub
		
	}
	
	public double getDamageDealt(){
		return damageDealt;
	}
	
	
	
	

	

}
