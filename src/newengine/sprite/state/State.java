package newengine.sprite.state;

import commons.point.GamePoint;
import newengine.player.Player;
import newengine.sprite.SpriteID;

public class State {
	
	private SpriteID id = null;
	private Player player = null;
	private boolean isAttacker = false;
	private double cooldown = -1.0;
	private int damageStrength = -1;
	private int health = -1;
	private GamePoint pos = null;
	private double range = -1;
	private double speed = -1;
	
	public State(SpriteID id, Player player, boolean isAttacker, double cooldown, int damageStrength, int health,
			GamePoint pos, double range, double speed){
		this.id = id;
		this.player = player;
		this.isAttacker = isAttacker;
		this.cooldown = cooldown;
		this.damageStrength = damageStrength;
		this.health = health;
		this.pos = pos;
		this.range = range;
		this.speed = speed;
	}
	
	public State() {
		
	}
	
	public void setID(SpriteID id){
		this.id = id;
	}
	public void setPlayer(Player player){
		this.player = player;
	}
	public void setIsAttacker(boolean isAttacker){
		this.isAttacker = isAttacker;
	}
	public void setCooldown(double cooldown){
		this.cooldown = cooldown;
	}
	public void setDamageStrength(int damageStrength){
		this.damageStrength = damageStrength;
	}
	public void setHealth(int health){
		this.health = health;
	}
	public void setPos(GamePoint pos){
		this.pos = pos;
	}
	public void setRange(double range){
		this.range = range;
	}
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public SpriteID getID(){
		return id;
	}
	public Player getPlayer(){
		return player;
	}
	public boolean isAttacker(){
		return isAttacker;
	}
	public double getCooldown(){
		return cooldown;
	}
	public int getDamageStrength(){
		return damageStrength;
	}
	public int getHealth(){
		return health;
	}
	public GamePoint getPos(){
		return pos;
	}
	public double getRange(){
		return range;
	}
	public double getSpeed(){
		return speed;
	}
	
	

}
