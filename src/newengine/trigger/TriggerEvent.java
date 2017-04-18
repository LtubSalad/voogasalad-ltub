package newengine.trigger;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.SpriteID;

public class TriggerEvent {
	public enum TriggerEventType {
		GAME, SPRITE_ALL, SPRITE_SPECIFIC
	}

	private TriggerEventType triggerEventType;
	private BusEventType<?> busEventType;
	private SpriteID spriteID;
	
	
	public TriggerEvent(TriggerEventType triggerEventType, BusEventType<?> busEventType) {
		this(triggerEventType, busEventType, null);
	}
	public TriggerEvent(TriggerEventType triggerEventType, BusEventType<?> busEventType, SpriteID spriteID) {
		this.triggerEventType = triggerEventType;
		this.busEventType = busEventType;
		this.spriteID = spriteID;
	}
	
		
	public TriggerEventType getType() {
		return triggerEventType;
	}
	public BusEventType<? extends BusEvent> getBusEventType() {
		return busEventType;
	}
	public SpriteID getSpriteID() {
		return spriteID;
	}
	
}
