package newengine.events.stats;

import bus.BusEvent;
import bus.BusEventType;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.player.Player;

public class ChangeWealthEvent extends ChangeStatsEvent {
	public static final BusEventType<ChangeWealthEvent> CHANGE = new BusEventType<>(ChangeWealthEvent.class.getName() + "CHANGE");
	private WealthType wealthType;
	
	public ChangeWealthEvent(BusEventType<? extends BusEvent> busEventType, Player player, WealthType wealthType, int amountChanged) {
		super(busEventType, player, amountChanged);
		this.wealthType = wealthType;
	}
	
//	public ChangeWealthEvent(BusEventType<? extends BusEvent> busEventType, String playerName, WealthType wealthType, int amountChanged) {
//		super(busEventType, playerName, amountChanged);
//		this.wealthType = wealthType;
//	}
	
	public WealthType getWealthType(){
		return wealthType;
	}
}
