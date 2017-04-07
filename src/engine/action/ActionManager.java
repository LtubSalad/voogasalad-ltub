package engine.action;

import bus.EventBus;
import engine.action.events.MoveSpriteEvent;
import engine.skill.Target;
import engine.sprite.Sprite;

public class ActionManager {

	private EventBus bus;
	
	public ActionManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(MoveSpriteEvent.READY, (e) -> {
			ActionMode actionMode = e.getActionMode();
			Sprite sprite = e.getSprite();
			Target target = e.getTarget();
			// TODO: currently assume target is position.
			sprite.getMovable().ifPresent((movable) -> {
				if (actionMode == ActionMode.QUEUE) {
					sprite.queueAction(() -> movable.moveTo(target.getLocation()));
				} else {
					sprite.executeAction(() -> movable.moveTo(target.getLocation()));
				}
			});
		});
	}
	
}
