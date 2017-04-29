package newengine.sprite.state;

import bus.EventBus;
import commons.point.GamePoint;
import newengine.player.Player;
import newengine.sprite.Sprite;
import newengine.sprite.SpriteID;
import newengine.sprite.components.GameBus;

public class SpriteState {
	
	private Sprite sprite;
	private SpriteID id = null;
	private Player player = null;
	private boolean isAttacker = false;
	private double cooldown = -1.0;
	private int damageStrength = -1;
	private int health = -1;
	private double xpos = 0;
	private double ypos = 0;
	private double range = -1;
	private double speed = -1;
	
	private EventBus bus;
	
	public SpriteState(Sprite sprite) {
		this.sprite = sprite;
		bus = sprite.getComponent(GameBus.TYPE).get().getGameBus();
		initHandlers();
	}
	
	private void initHandlers(){
		
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
	public void setXPos(double xpos){
		this.xpos = xpos;
	}
	public void setYPos(double ypos){
		this.ypos = ypos;
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
	public double getXPos(){
		return xpos;
	}
	public double getYPos(){
		return ypos;
	}
	public double getRange(){
		return range;
	}
	public double getSpeed(){
		return speed;
	}
	
	

}
