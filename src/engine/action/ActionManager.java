package engine.action;

import bus.EventBus;
import commons.Point;
import engine.player.Player;
import engine.sprite.Sprite;

public class ActionManager {

	private EventBus bus;
	
	public ActionManager(EventBus bus) {
		this.bus = bus;
	}
	
	// TODO: it could be a separate action filter class.
	public boolean moveSpriteToAllowed(Player actionSender, Sprite sprite, Point dest) {
		if (actionSender == null || sprite == null || dest == null) { return false; }
		if (!actionSender.canControl(sprite.getPlayer())) { return false; }
		if (!sprite.getMovable().isPresent()) { return false; }
		return true;
	}
	
	public void moveSpriteTo(ActionMode actionMode, Player actionSender, Sprite sprite, Point dest) {
		if (!moveSpriteToAllowed(actionSender, sprite, dest)) { return; }
		sprite.getMovable().ifPresent((movable) -> {
			if (actionMode == ActionMode.QUEUE) {
				sprite.queueAction(() -> movable.moveTo(dest));
			} else {
				sprite.executeAction(() -> movable.moveTo(dest));
			}
		});
	}
	
}
