package newengine.events.stats;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.player.Player;

public class ChangeWealthEvent extends ChangeStatsEvent {
	public static final BusEventType<ChangeWealthEvent> SPECIFIC = new BusEventType<>(ChangeWealthEvent.class.getName() + "SPECIFIC");
	private String wealthType;
	
	public ChangeWealthEvent(BusEventType<? extends BusEvent> busEventType, Player player, int amountChanged, String wealthType) {
		super(busEventType, player, amountChanged);
		this.wealthType = wealthType;
	}
	
	public String getWealthType(){
		return wealthType;
	}
}
