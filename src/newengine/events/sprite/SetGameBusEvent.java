package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;
import bus.EventBus;

public class SetGameBusEvent extends BusEvent {

	public static final BusEventType<SetGameBusEvent> ANY = new BusEventType<>(SetGameBusEvent.class.getName()+"ANY");
	
	private EventBus gameBus;

	public SetGameBusEvent(EventBus gameBus) {
		super(ANY);
		this.gameBus = gameBus;
	}
	
	public EventBus getGameBus() {
		return gameBus;
	}
	
}
