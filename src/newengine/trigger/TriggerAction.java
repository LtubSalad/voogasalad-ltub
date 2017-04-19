package newengine.trigger;

import bus.BusEvent;
import newengine.sprite.SpriteID;

public class TriggerAction {
	public enum TriggerActionType {
		GAME, SPRITE_BROADCAST, SPRITE_SPECIFIC, SPRITE_TRIGGERING_UNIT
	}

	private TriggerActionType actionType;
	private BusEvent busEvent;
	private SpriteID spriteID;
	
	public TriggerAction(TriggerActionType actionType, BusEvent busEvent) {
		this(actionType, busEvent, null);
	}
	public TriggerAction(TriggerActionType actionType, BusEvent busEvent, SpriteID spriteID) {
		this.actionType = actionType;
		this.busEvent = busEvent;
		this.spriteID = spriteID;
	}

	
	public TriggerActionType getType() {
		return actionType;
	}
	public BusEvent getBusEvent() {
		return busEvent;
	}
	public SpriteID getSpriteID() {
		return spriteID;
	}
}
