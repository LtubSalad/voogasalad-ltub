package newengine.events.trigger;

import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;
import newengine.sprite.SpriteID;

public class SpriteTriggerRegisterEvent extends BusEvent {

	public static final BusEventType<SpriteTriggerRegisterEvent> SPRITE_ALL = new BusEventType<>(
			SpriteTriggerRegisterEvent.class.getName() + "SPRITE_ALL");
	public static final BusEventType<SpriteTriggerRegisterEvent> SPRITE_SPECIFIC = new BusEventType<>(
			SpriteTriggerRegisterEvent.class.getName() + "SPRITE_SPECIFIC");

	private BusEventType<? extends BusEvent> triggerBusEventType; 
	private BusEventHandler<BusEvent> triggerHandler;
	private SpriteID spriteID;
	
	private SpriteTriggerRegisterEvent(BusEventType<? extends BusEvent> busEventType, 
			BusEventType<? extends BusEvent> triggerBusEventType,
			BusEventHandler<BusEvent> triggerHandler, SpriteID spriteID) {
		super(busEventType);
		this.triggerBusEventType = triggerBusEventType;
		this.triggerHandler = triggerHandler;
		this.spriteID = spriteID;
	}
	public static SpriteTriggerRegisterEvent spriteAllRegisterEvent(BusEventType<? extends BusEvent> triggerBusEventType, 
			BusEventHandler<BusEvent> triggerHandler) {
		return new SpriteTriggerRegisterEvent(SPRITE_ALL, triggerBusEventType, triggerHandler, null);
	}
	public static SpriteTriggerRegisterEvent spriteSpecificRegisterEvent(BusEventType<? extends BusEvent> triggerBusEventType,
			BusEventHandler<BusEvent> triggerHandler, SpriteID spriteID) {
		return new SpriteTriggerRegisterEvent(SPRITE_SPECIFIC, triggerBusEventType, triggerHandler, spriteID);
	}
	
	public BusEventType<? extends BusEvent> getTriggerBusEventType() {
		return triggerBusEventType;
	}
	public BusEventHandler<BusEvent> getTriggerHandler() {
		return triggerHandler;
	}
	public SpriteID getSpriteID() {
		return spriteID;
	}

}
