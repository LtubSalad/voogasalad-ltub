package newengine.sprite.state;

import bus.EventBus;
import commons.point.GamePoint;
import newengine.events.sprite.StateChangeEvent;
import newengine.player.Player;
import newengine.sprite.Sprite;
import newengine.sprite.SpriteID;
import newengine.sprite.components.GameBus;

public class SpriteState {
	
	private static final int NAN = -10000000;
	private Sprite sprite;
	private SpriteID id = null;
	private Player player = null;
	private boolean isAttacker = false;
	private double cooldown = NAN;
	private int damageStrength = NAN;
	private int health = NAN;
	private double xpos = NAN;
	private double ypos = NAN;
	private double range = NAN;
	private double speed = NAN;
	
	public SpriteState(Sprite sprite) {
		this.sprite = sprite;
		initHandlers();
	}
	
	private void initHandlers(){
		sprite.on(StateChangeEvent.ID, e -> {
			e.getSprite().getState().setID(e.getNewID());
		});
		sprite.on(StateChangeEvent.PLAYER, e -> {
			e.getSprite().getState().setPlayer(e.getNewPlayer());
		});
		sprite.on(StateChangeEvent.ATTACKER, e -> {
			e.getSprite().getState().setIsAttacker(e.getNewBoolean());
		});
		sprite.on(StateChangeEvent.COOLDOWN, e -> {
			e.getSprite().getState().setCooldown(e.getNewDouble());
		});
		sprite.on(StateChangeEvent.DAMAGESTRENGTH, e -> {
			this.damageStrength = e.getNewInt();
		});
		sprite.on(StateChangeEvent.HEALTH, e -> {
			System.out.println("state - health was changed to " + e.getNewInt());
			this.health = e.getNewInt();
		});
		sprite.on(StateChangeEvent.XPOS, e -> {
			System.out.println("state - xpos is changed to " + Math.round(e.getNewDouble()));
			this.xpos = Math.round(e.getNewDouble());
		});
		sprite.on(StateChangeEvent.YPOS, e -> {
			this.ypos = Math.round(e.getNewDouble());
		});
		sprite.on(StateChangeEvent.RANGE, e -> {
			this.range = e.getNewDouble();
		});
		sprite.on(StateChangeEvent.SPEED, e -> {
			this.speed = e.getNewDouble();
		});
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
