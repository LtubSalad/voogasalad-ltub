package engine.sprite.attacker;


import engine.camera.GamePoint;
import engine.model.SpriteModelEvent;
import engine.sprite.Attribute;
import engine.sprite.Sprite;
import engine.sprite.ai.AI;
import engine.sprite.collidable.Collidable;
import engine.sprite.collidable.CollisionBound;
import engine.sprite.healthholder.HealthHolder;
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

/**
 * The attacker class is an attribute of a sprite that defines a sprite's attacking characteristics if it has any. It's prime
 * function is to be able to fire off the weapon for a sprite if it is found to be in the range of another sprite
 * @author Matthew Tribby, Alison Huang, Yilin Gao
 * ^ not exactly sure who helped write this class, feel free to add yourself
 */
public class Attacker implements Attribute {
	private double radius = 150;
	private int damage = 23498;
	private EventBus bus;
	private double reloadPeriod = 0.5;
	private double timeRemaining = 0;


	/**
	 * Constructor which initializes the two main instance variables of the attribute
	 * @param radius range of the attacker
	 * @param damage Damage that the attacker can deal
	 */
	public Attacker(double radius, int damage){
		this.radius = radius;
		this.damage = damage;
	}
	
	/**
	 * Blank constructor
	 */
	public Attacker() {
		
	}
	
	/**
	 * Sets the event bus of this attribute, necessary for firing events
	 * @param bus
	 */
	public void setBus(EventBus bus){
		this.bus = bus;
	}
	
	/**
	 * Returns the current radius of the sprite's attacking capabilities
	 * @return double range
	 */
	public double getRange(){
		return this.radius;
	}

	/**
	 * Sets the range of the attacking capabilities of a sprite
	 * @param detectionRange
	 */
	public void setRange(double detectionRange) {
		this.radius = detectionRange;
	}
	
	/**
	 * Sets the time it takes to reload (the time needed between sprite attacks)
	 * @param time
	 */
	public void setReloadPeriod(double time) {
		reloadPeriod = time;
	}
	
	/**
	 * Updates the time. This is a useful method when working in  a set time, like in a game loop frame
	 * @param dt Elapsed time
	 */
	public void update(double dt){
		timeRemaining -= dt;
	}
	
	/**
	 * Creates a weapon to fire. A weapon is a sprite as well and this method creates a weapon that originates
	 * from this sprite and goes to a target
	 * @param shooter Originator of weapon
	 * @param target Target of weapon
	 * 
	 * TODO hard-coded weapon
	 * TODO this method is extremely long
	 */
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
