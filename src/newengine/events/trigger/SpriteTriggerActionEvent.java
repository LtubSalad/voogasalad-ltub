package newengine.events.trigger;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.SpriteID;

public class SpriteTriggerActionEvent extends BusEvent {

	public static final BusEventType<SpriteTriggerActionEvent> BROADCAST = new BusEventType<>(
			SpriteTriggerActionEvent.class.getName() + "BROADCAST");
	public static final BusEventType<SpriteTriggerActionEvent> SPECIFIC = new BusEventType<>(
			SpriteTriggerActionEvent.class.getName() + "SPECIFIC");
	
	private BusEvent event;
	private SpriteID spriteID;
	
	private SpriteTriggerActionEvent(BusEventType<? extends BusEvent> busEventType, BusEvent event, SpriteID spriteID) {
		super(busEventType);
		this.event = event;
		this.spriteID = spriteID;
	}
	public static SpriteTriggerActionEvent createBroadcastEvent(BusEvent event) {
		return new SpriteTriggerActionEvent(BROADCAST, event, null);
	}
	public static SpriteTriggerActionEvent createSpecificEvent(BusEvent event, SpriteID spriteID) {
		return new SpriteTriggerActionEvent(SPECIFIC, event, spriteID);
	}
	
	public BusEvent getEvent() {
		return event;
	}
	public SpriteID getSpriteID() {
		return spriteID;
	}
	

}
