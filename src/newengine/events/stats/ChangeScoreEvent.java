package newengine.events.stats;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.player.Player;

public class ChangeScoreEvent extends ChangeStatsEvent {
	public static final BusEventType<ChangeScoreEvent> SPECIFIC = new BusEventType<>(ChangeScoreEvent.class.getName() + "SPECIFIC");

	public ChangeScoreEvent(BusEventType<? extends BusEvent> busEventType, Player player, int changeInScore) {
		super(busEventType, player, changeInScore);
	}
}
