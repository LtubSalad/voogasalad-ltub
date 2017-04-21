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

	private TriggerEvent(TriggerEventType triggerEventType, BusEventType<?> busEventType, SpriteID spriteID) {
		this.triggerEventType = triggerEventType;
		this.busEventType = busEventType;
		this.spriteID = spriteID;
	}
	public static TriggerEvent createGameTriggerEvent(BusEventType<?> busEventType) {
		return new TriggerEvent(TriggerEventType.GAME, busEventType, null);
	}
	public static TriggerEvent createSpriteAllTriggerEvent(BusEventType<?> busEventType) {
		return new TriggerEvent(TriggerEventType.SPRITE_ALL, busEventType, null);
	}
	public static TriggerEvent createSpriteSpecificTriggerEvent(BusEventType<?> busEventType, SpriteID spriteID) {
		return new TriggerEvent(TriggerEventType.SPRITE_SPECIFIC, busEventType, spriteID);
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
