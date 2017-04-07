package engine.input.events;

import bus.BusEvent;
import bus.BusEventType;
import engine.action.ActionMode;
import engine.player.Player;
import engine.skill.Target;

public class GameWorldMouseEvent extends BusEvent {

	public static final BusEventType<GameWorldMouseEvent> CONFIRM_SKILL = new BusEventType<>("CONFIRM_SKILL");
	public static final BusEventType<GameWorldMouseEvent> CANCEL_SKILL_AND_MOVE_SPRITE = new BusEventType<>("CANCEL_SKILL_AND_MOVE_SPRITE");
	public static final BusEventType<GameWorldMouseEvent> SELECT_SPRITE = new BusEventType<>("SELECT_SPRITE");
	

	
	private Target target;
	private ActionMode actionMode;
	private Player player;

	public GameWorldMouseEvent(BusEventType<? extends BusEvent> busEventType, Target target, ActionMode actionMode,
			Player player) {
		super(busEventType);
		this.target = target;
		this.actionMode = actionMode;
		this.player = player;
	}

	public Target getTarget() {
		return target;
	}
	public ActionMode getActionMode() {
		return actionMode;
	}
	public Player getPlayer() {
		return player;
	}

}
