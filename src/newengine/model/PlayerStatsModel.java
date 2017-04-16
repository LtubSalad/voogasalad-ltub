package newengine.model;

import bus.EventBus;
import newengine.utils.variable.Var;

public class PlayerStatsModel {
	// TODO: which player is this
	
	private EventBus bus;
	private final Var<Integer> goldVar = new Var<>();
	
	public PlayerStatsModel(EventBus bus) {
		this.bus = bus;
	}
	
	public double getGold() {
		return goldVar.get();
	}

}
