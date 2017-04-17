package newengine.trigger;

import java.util.Optional;

import bus.BusEventType;
import newengine.sprite.SpriteID;

public class TriggerEvent {
	public enum TriggerEventType {
		GAME, SPRITE_ALL, SPRITE_SPECIFIC
	}
	
	private TriggerEventType type;
	private BusEventType<?> busEventType;
	private SpriteID spriteID = null;
	
	public TriggerEvent(TriggerEventType type, BusEventType<?> busEventType) {
		this.type = type;
		this.busEventType = busEventType;
	}
	
	public void setSpriteID(SpriteID spriteID) {
		this.spriteID = spriteID;
	}
	public Optional<SpriteID> getSpriteID() {
		return Optional.ofNullable(spriteID);
	}
	
	public TriggerEventType getTriggerEventType() {
		return type;
	}
	public BusEventType<?> getBusEventType() {
		return busEventType;
	}
	
}
