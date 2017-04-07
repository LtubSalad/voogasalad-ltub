package engine.action;

import bus.EventBus;
import engine.player.Player;
import engine.sprite.Sprite;

public class PlayerActionManager {

	private EventBus bus;
	
	public PlayerActionManager(EventBus bus) {
		this.bus = bus;
	}
	
}
