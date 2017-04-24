package newengine.events.player;

import bus.BusEvent;
import bus.BusEventType;
import newengine.player.Player;

public class MainPlayerEvent extends BusEvent{
	
	public static final BusEventType<MainPlayerEvent> ANY = new BusEventType<>(
			MainPlayerEvent.class.getName() + "ANY");

	private Player player;
	public MainPlayerEvent(Player player) {
		super(ANY);
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
}
