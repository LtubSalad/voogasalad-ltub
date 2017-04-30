package newengine.events.stats;

import bus.BusEvent;
import bus.BusEventType;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.player.Player;

public class InsufficientGoldEvent extends BusEvent {
	
	public static final BusEventType<InsufficientGoldEvent> CHECK = new BusEventType<>(InsufficientGoldEvent.class.getName() + "CHECK");
	public static final BusEventType<InsufficientGoldEvent> ANY = new BusEventType<>(InsufficientGoldEvent.class.getName() + "ANY");
	
	private Player player;
	private WealthType type;

	public InsufficientGoldEvent(BusEventType<? extends BusEvent> busEventType, Player player, WealthType type) {
		super(busEventType);
		this.player = player;
		this.type = type;
	}

	public InsufficientGoldEvent(BusEventType<? extends BusEvent> busEventType) {
		super(busEventType);
	}

	public Player getPlayer() {
		return player;
	}
	
	public WealthType getType() {
		return type;
	}
	
}
