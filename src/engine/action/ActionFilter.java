package engine.action;

import bus.EventBus;
import engine.action.events.MoveSpriteEvent;
import engine.player.Player;
import engine.skill.Target;
import engine.sprite.Sprite;

/**
 * Checks whether an action could be initiated by a player.
 * @author keping
 *
 */
public class ActionFilter {
	
	private EventBus bus;
	
	public ActionFilter(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(MoveSpriteEvent.RAW, (e) -> {
			ActionMode actionMode = e.getActionMode();
			Player actionSender = e.getActionSender();
			Sprite sprite = e.getSprite();
			Target target = e.getTarget();
			if (!actionSender.canControl(sprite.getPlayer())) { return; }
			if (!sprite.getMovable().isPresent()) { return; }
			bus.emit(new MoveSpriteEvent(MoveSpriteEvent.READY, actionMode, actionSender, sprite, target));
		});
	}
	
}
