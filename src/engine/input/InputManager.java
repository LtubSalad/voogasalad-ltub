package engine.input;

import bus.EventBus;
import commons.Point;
import engine.action.ActionManager;
import engine.action.ActionMode;
import engine.input.events.KeyEvent;
import engine.input.events.MouseEvent;
import engine.model.Model;
import engine.player.Player;
import engine.playerstate.PlayerInputState;
import engine.playerstate.PlayerSelectionState;
import engine.playerstate.PlayerSelectionState.SelectionType;
import engine.sprite.Sprite;
import javafx.scene.input.KeyCode;

public class InputManager {

	private EventBus bus;
	private Model model;
	private SelectionChecker selectionChecker;
	private ActionManager actionManager;
	private PlayerInputState playerInputState;
	private PlayerSelectionState playerSelectionState;

	public InputManager(EventBus bus, Model model) {
		this.bus = bus;
		this.model = model;
		selectionChecker = new SelectionChecker();
	}

	public void setActionManager(ActionManager actionManager) {
		this.actionManager = actionManager;
	}
	public void setPlayerInputState(PlayerInputState playerInputState) {
		this.playerInputState = playerInputState;
	}
	public void setPlayerSelectionState(PlayerSelectionState playerSelectionState) {
		this.playerSelectionState = playerSelectionState;
	}
	

	public void initHandlers() {
		bus.on(KeyEvent.PRESS, e -> {
			playerInputState.pressKey(e.getCode());
		});
		bus.on(KeyEvent.RELEASE, e -> {
			playerInputState.releaseKey(e.getCode());
		});
		bus.on(MouseEvent.LEFT, e -> {
			Sprite selected = selectionChecker.getSelection(model, e.getPos());
			playerSelectionState.setSelectedSprite(selected);
		});
		bus.on(MouseEvent.RIGHT, e -> {
			if (playerSelectionState.getSelectionType() == SelectionType.SINGLE) {
				Sprite selected = playerSelectionState.getSelectedSprite();
				ActionMode actionMode = playerInputState.isKeyPressed(KeyCode.SHIFT) ? ActionMode.QUEUE
						: ActionMode.INSTANT;
				actionManager.moveSpriteTo(actionMode, Player.DEFAULT, selected, e.getPos());
			}
		});
	}
	
}
