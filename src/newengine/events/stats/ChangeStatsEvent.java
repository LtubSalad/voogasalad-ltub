package newengine.events.stats;

import bus.BusEvent;
import bus.BusEventType;
import newengine.player.Player;

public class ChangeStatsEvent extends BusEvent{
	private Player player;
	private int change;

	public ChangeStatsEvent(BusEventType<? extends BusEvent> busEventType, Player player, int amountChanged) {
		super(busEventType);
		this.player = player;
		this.change = amountChanged;
	}
	
//	public ChangeStatsEvent(BusEventType<? extends BusEvent> busEventType, String playerName, int amountChanged) {
//		super(busEventType);
//		this.player = playerName;
//		this.change = amountChanged;
//	}
	
	public Player getPlayer(){
		return player;
	}
	
	public int getAmountChanged(){
		return change;
	}

}
