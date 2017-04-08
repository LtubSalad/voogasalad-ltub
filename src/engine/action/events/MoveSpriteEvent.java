package engine.action.events;

import bus.BusEvent;
import bus.BusEventType;
import engine.action.ActionMode;
import engine.player.Player;
import engine.skill.Target;
import engine.sprite.Sprite;

public class MoveSpriteEvent extends BusEvent {

	/**
	 * Unfiltered, not necessarily executed.
	 */
	public static final BusEventType<MoveSpriteEvent> RAW = new BusEventType<>("MOVE_SPRITE_RAW");
	/**
	 * Filtered, will be executed when fired.
	 */
	public static final BusEventType<MoveSpriteEvent> READY = new BusEventType<>("MOVE_SPRITE_READY");

	private ActionMode actionMode;
	private Player actionSender;
	private Sprite sprite;
	private Target target;
	
	public MoveSpriteEvent(BusEventType<? extends BusEvent> busEventType, ActionMode actionMode, Player actionSender,
			Sprite sprite, Target target) {
		super(busEventType);
		this.actionMode = actionMode;
		this.actionSender = actionSender;
		this.sprite = sprite;
		this.target = target;
	}
	
	public ActionMode getActionMode() {
		return actionMode;
	}
	public Player getActionSender() {
		return actionSender;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public Target getTarget() {
		return target;
	}

}
