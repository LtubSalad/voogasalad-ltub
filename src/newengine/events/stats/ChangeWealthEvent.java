package newengine.events.stats;

import bus.BusEvent;
import bus.BusEventType;
import newengine.player.Player;

public class ChangeWealthEvent extends ChangeStatsEvent {
	public static final BusEventType<ChangeWealthEvent> CHANGE = new BusEventType<>(ChangeWealthEvent.class.getName() + "CHANGE");
	private String wealthType;
	
	public ChangeWealthEvent(BusEventType<? extends BusEvent> busEventType, Player player, String wealthType, int amountChanged) {
		super(busEventType, player, amountChanged);
		this.wealthType = wealthType;
	}
	
	public ChangeWealthEvent(BusEventType<? extends BusEvent> busEventType, String playerName, String wealthType, int amountChanged) {
		super(busEventType, playerName, amountChanged);
		this.wealthType = wealthType;
	}
	
	public String getWealthType(){
		return wealthType;
	}
}
