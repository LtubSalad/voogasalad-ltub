package newengine.model;

import bus.EventBus;
import newengine.events.GameInitializationEvent;
import newengine.events.player.MainPlayerEvent;
import newengine.player.Player;

public class PlayerRelationModel {

	private EventBus bus;
	private Player mainPlayer;
	
	public PlayerRelationModel(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(MainPlayerEvent.ANY, (e) -> {
			setMainPlayer(e.getPlayer());
		});
	}

	private void setMainPlayer(Player player) {
		this.mainPlayer = player;
	}
	
	public Player getMainPlayer() {
		return this.mainPlayer;
	}
}
