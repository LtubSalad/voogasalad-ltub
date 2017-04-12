package engine.sprite.attack;


import engine.camera.GamePoint;
import engine.model.SpriteModelEvent;
import engine.sprite.Attribute;
import engine.sprite.Sprite;
import engine.sprite.ai.AI;
import engine.sprite.collision.Collidable;
import engine.sprite.collision.CollisionBound;
import engine.sprite.health.HealthHolder;
import engine.sprite.images.ImageSet;
import engine.sprite.images.LtubImage;
import engine.sprite.movable.Movable;
import engine.sprite.weapon.Weapon;
import gameDevelopmentInterface.Path;

import java.util.LinkedList;
import java.util.Queue;

import bus.EventBus;
import commons.MathUtils;
import data.AttributeData;

public class Attacker implements Attribute {
	private double radius = 150;
	private EventBus bus;
	private double reloadPeriod = 0.5;
	private double timeRemaining = 0;
	
	public Attacker(AttributeData data){
		this.radius = Double.parseDouble(data.getVariable("radius"));
	}
	
	public Attacker(){

	}
	
	public void setBus(EventBus bus){
		this.bus = bus;
	}
	
	public double getRange(){
		return this.radius;
	}

	public void setRange(double detectionRange) {
		this.radius = detectionRange;
	}
	
	public void setReloadPeriod(double time) {
		reloadPeriod = time;
	}
	
	public void update(double dt){
		timeRemaining -= dt;
	}
	
	public void createWeapon(Sprite shooter, Sprite target){
		if (!weaponReloaded()){
			return;
		}
		resetTimeRemaining();
		Sprite weaponSprite = new Sprite();
		weaponSprite.setPos(shooter.getPos());
		LtubImage image1 = new LtubImage("images/characters/bullet.png");
		ImageSet imageSet1 = new ImageSet();
		imageSet1.setImage(image1);
		weaponSprite.setImageSet(imageSet1);
		Weapon weaponAttribute = new Weapon(shooter, target);
		weaponAttribute.setAttackPower(60);
		Movable movableAttribute = new Movable();
		movableAttribute.setSpeed(600);
		weaponSprite.setWeapon(weaponAttribute);
		weaponSprite.setMovable(movableAttribute);
		Path path1 = new Path();
		Queue<GamePoint> q = new LinkedList<GamePoint>();
		q.add(target.getPos());
		path1.changePath(q);
		weaponSprite.setAI(new AI(path1));
		weaponSprite.setCollidable(new Collidable(new CollisionBound(image1)));

		weaponSprite.setHitsTarget(() -> {
			bus.emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, weaponSprite));
			
			HealthHolder hh = target.getHealthHolder().get();
			double amt = weaponAttribute.getAttackPower();			
			hh.decrementHealth(amt);
			
			if (hh.getHealth() <= 0){
				bus.emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, target));
			}
			
		});
		
		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, weaponSprite));
	}

	private boolean weaponReloaded() {
		return (timeRemaining < 0);
	}
	
	private void resetTimeRemaining(){
		timeRemaining = reloadPeriod;
	}

}
