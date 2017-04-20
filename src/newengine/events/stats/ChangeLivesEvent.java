package newengine.events.stats;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.player.Player;

public class ChangeLivesEvent extends ChangeStatsEvent{
	public static final BusEventType<ChangeLivesEvent> CHANGE = new BusEventType<>(ChangeLivesEvent.class.getName() + "CHANGE");
	public static final BusEventType<ChangeLivesEvent> SET = new BusEventType<>(ChangeLivesEvent.class.getName() + "SET");

	public ChangeLivesEvent(BusEventType<? extends BusEvent> busEventType, String playerName, int changeInLives) {
		super(busEventType, playerName, changeInLives);
	}

}
