package newengine.events.stats;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.player.Player;

public class ChangeStatsEvent extends BusEvent{
	private Player player;
	private int change;

	public ChangeStatsEvent(BusEventType<? extends BusEvent> busEventType, Player player, int amountChanged) {
		super(busEventType);
		this.player = player;
		this.change = amountChanged;
	}
	
	public String getPlayerName(){
		return player.getName();
	}
	
	public int getAmountChanged(){
		return change;
	}

}
