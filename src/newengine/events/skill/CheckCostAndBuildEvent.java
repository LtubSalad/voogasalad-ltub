package newengine.events.skill;

import bus.BusEvent;
import bus.BusEventType;
import newengine.player.Player;
import newengine.utils.Callback;

public class CheckCostAndBuildEvent extends BusEvent {

	public static final BusEventType<CheckCostAndBuildEvent> ANY = new BusEventType<>(CheckCostAndBuildEvent.class.getName()+"ANY");

	private int cost; // TODO: currently the cost is only gold
	private Player player;
	private Callback buildCallback;
	
	public CheckCostAndBuildEvent(int cost, Player player, Callback buildCallback) {
		super(ANY);
		this.cost = cost;
		this.player = player;
		this.buildCallback = buildCallback;
	}

	public int getCost() {
		return cost;
	}
	public Player getPlayer() {
		return player;
	}
	public Callback getBuildCallback() {
		return buildCallback;
	}
	
}
