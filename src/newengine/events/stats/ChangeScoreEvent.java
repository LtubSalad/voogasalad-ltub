package newengine.events.stats;

import bus.BusEvent;
import bus.BusEventType;
import newengine.player.Player;

public class ChangeScoreEvent extends ChangeStatsEvent {
	public static final BusEventType<ChangeScoreEvent> CHANGE = new BusEventType<>(ChangeScoreEvent.class.getName() + "CHANGE");
	public static final BusEventType<ChangeScoreEvent> SET = new BusEventType<>(ChangeScoreEvent.class.getName() + "SET");

	public ChangeScoreEvent(BusEventType<? extends BusEvent> busEventType, Player player, int changeInScore) {
		super(busEventType, player, changeInScore);
	}
	
	public ChangeScoreEvent(BusEventType<? extends BusEvent> busEventType, String playerName, int changeInScore) {
		super(busEventType, playerName, changeInScore);
	}
	
	
}
