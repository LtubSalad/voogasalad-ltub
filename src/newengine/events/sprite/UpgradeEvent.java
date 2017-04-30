package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.Sprite;

public class UpgradeEvent extends BusEvent {
	
	public static final BusEventType<UpgradeEvent> RESET = new BusEventType<>(
			UpgradeEvent.class.getName() + "RESET");
	public static final BusEventType<UpgradeEvent> DOUBLE = new BusEventType<>(
			UpgradeEvent.class.getName() + "DOUBLE");


	
	private int value;
	private Sprite sprite;
	
	public UpgradeEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite, int value) {
		super(busEventType);
		this.sprite = sprite;
		this.value = value;
	}
	
	public UpgradeEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite){
		super(busEventType);
		this.sprite = sprite;
	}
	
	public int getValue(){
		return value;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	

}
