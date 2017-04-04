package engine.input;

import bus.EventBus;
import commons.Point;
import engine.player.Player;
import engine.sprite.Sprite;

public class ActionManager {

	private EventBus bus;
	
	public ActionManager(EventBus bus) {
		this.bus = bus;
	}
	
	public boolean moveSpriteToAllowed(Player actionSender, Sprite sprite, Point dest) {
		if (actionSender == null || sprite == null || dest == null) { return false; }
		if (!actionSender.canControlPlayer(sprite.getPlayer())) { return false; }
		if (!sprite.getMovable().isPresent()) { return false; }
		return true;
	}
	
	public void moveSpriteTo(Player actionSender, Sprite sprite, Point dest) {
		// TODO: could be a separate action filter before.
		if (!moveSpriteToAllowed(actionSender, sprite, dest)) { return; }
		sprite.getMovable().ifPresent((movable) -> {
			movable.moveTo(dest);
		});
	}
	
}
