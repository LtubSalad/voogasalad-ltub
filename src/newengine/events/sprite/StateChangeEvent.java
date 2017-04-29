package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;
import newengine.player.Player;
import newengine.sprite.Sprite;
import newengine.sprite.SpriteID;

public class StateChangeEvent extends BusEvent {

	public static final BusEventType<StateChangeEvent> ID = new BusEventType<>(
			StateChangeEvent.class.getName() + "ID");
	public static final BusEventType<StateChangeEvent> PLAYER = new BusEventType<>(
			StateChangeEvent.class.getName() + "PLAYER");
	public static final BusEventType<StateChangeEvent> ATTACKER = new BusEventType<>(
			StateChangeEvent.class.getName() + "ATTACKER");
	public static final BusEventType<StateChangeEvent> COOLDOWN = new BusEventType<>(
			StateChangeEvent.class.getName() + "COOLDOWN");
	public static final BusEventType<StateChangeEvent> DAMAGESTRENGTH = new BusEventType<>(
			StateChangeEvent.class.getName() + "DAMAGESTRENGTH");
	public static final BusEventType<StateChangeEvent> HEALTH = new BusEventType<>(
			StateChangeEvent.class.getName() + "HEALTH");
	public static final BusEventType<StateChangeEvent> XPOS = new BusEventType<>(
			StateChangeEvent.class.getName() + "XPOS");
	public static final BusEventType<StateChangeEvent> YPOS = new BusEventType<>(
			StateChangeEvent.class.getName() + "YPOS");
	public static final BusEventType<StateChangeEvent> RANGE = new BusEventType<>(
			StateChangeEvent.class.getName() + "RANGE");
	public static final BusEventType<StateChangeEvent> SPEED = new BusEventType<>(
			StateChangeEvent.class.getName() + "SPEED");
	
	private Sprite sprite;
	private SpriteID newID;
	private Player newPlayer;
	private boolean newIsAttacker;
	private double newDouble;
	private int newInt;
	
	public StateChangeEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite, SpriteID ID) {
		super(busEventType);
		this.sprite = sprite;
		this.newID = ID;
	}
	
	public StateChangeEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite, Player player) {
		super(busEventType);
		this.sprite = sprite;
		this.newPlayer = player;
	}
	
	public StateChangeEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite, boolean isAttacker) {
		super(busEventType);
		this.sprite = sprite;
		this.newIsAttacker = isAttacker;
	}
	
	public StateChangeEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite, double newDouble) {
		super(busEventType);
		this.sprite = sprite;
		this.newDouble = newDouble;
	}
	
	public StateChangeEvent(BusEventType<? extends BusEvent> busEventType, int newInt) {
		super(busEventType);
		this.newInt = newInt;
	}
	
	public SpriteID getNewID(){
		return newID;
	}
	
	public Player getNewPlayer(){
		return newPlayer;
	}
	
	public boolean getNewBoolean(){
		return newIsAttacker;
	}
	
	public double getNewDouble(){
		return newDouble;
	}
	
	public int getNewInt(){
		return newInt;
	}
	
	public Sprite getSprite(){
		return sprite;
	}

}
