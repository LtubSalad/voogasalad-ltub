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
	
	private TriggerAction(TriggerActionType actionType, BusEvent busEvent, SpriteID spriteID) {
		this.actionType = actionType;
		this.busEvent = busEvent;
		this.spriteID = spriteID;
	}

	public static TriggerAction createGameTriggerAction(BusEvent busEvent) {
		return new TriggerAction(TriggerActionType.GAME, busEvent, null);
	}
	public static TriggerAction createSpriteBroadcastTriggerAction(BusEvent busEvent) {
		return new TriggerAction(TriggerActionType.SPRITE_BROADCAST, busEvent, null);
	}
	public static TriggerAction createSpriteSpecificTriggerAction(BusEvent busEvent, SpriteID spriteID) {
		return new TriggerAction(TriggerActionType.SPRITE_SPECIFIC, busEvent, spriteID);
	}
	public static TriggerAction createSpriteTriggeringUnitTriggerAction(BusEvent busEvent) {
		return new TriggerAction(TriggerActionType.SPRITE_TRIGGERING_UNIT, busEvent, null);
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
