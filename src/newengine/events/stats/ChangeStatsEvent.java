package newengine.events.stats;

import bus.BusEvent;
import bus.BusEventType;
import newengine.player.Player;

public class ChangeStatsEvent extends BusEvent{
	private String playerName;
	private int change;

	public ChangeStatsEvent(BusEventType<? extends BusEvent> busEventType, Player player, int amountChanged) {
		super(busEventType);
		this.playerName = player.getName();
		this.change = amountChanged;
	}
	
	public ChangeStatsEvent(BusEventType<? extends BusEvent> busEventType, String playerName, int amountChanged) {
		super(busEventType);
		this.playerName = playerName;
		this.change = amountChanged;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public int getAmountChanged(){
		return change;
	}

}
