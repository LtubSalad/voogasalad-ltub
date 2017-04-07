package engine.input;

import bus.EventBus;
import engine.action.ActionMode;
import engine.action.events.MoveSpriteEvent;
import engine.input.events.KeyEvent;
import engine.input.events.MouseEvent;
import engine.model.Model;
import engine.model.PlayerLocalModel;
import engine.player.Player;
import engine.playerstate.PlayerInputState;
import engine.playerstate.PlayerSelectionState;
import engine.playerstate.PlayerSelectionState.SelectionType;
import engine.playerstate.PlayerSkillState;
import engine.skill.Target;
import engine.skill.events.ConfirmSkillEvent;
import engine.skill.events.SelectSkillEvent;
import engine.sprite.Sprite;
import javafx.scene.input.KeyCode;

public class InputManager {

	private EventBus bus;
	private SelectionChecker selectionChecker;
	private Model model;
	private PlayerLocalModel localModel;

	public InputManager(EventBus bus, Model model, PlayerLocalModel localModel) {
		this.bus = bus;
		this.model = model;
		this.localModel = localModel;
		selectionChecker = new SelectionChecker();
	}

	private PlayerInputState playerInputState() {
		return localModel.getPlayerInputState();
	}
	private PlayerSelectionState playerSelectionState() {
		return localModel.getPlayerSelectionState();
	}
	private PlayerSkillState playerSkillState() {
		return localModel.getPlayerSkillState();
	}


	public void initHandlers() {
		bus.on(KeyEvent.PRESS, e -> {
			playerInputState().pressKey(e.getCode());
		});
		bus.on(KeyEvent.RELEASE, e -> {
			playerInputState().releaseKey(e.getCode());
		});
		bus.on(MouseEvent.LEFT, e -> {
			// left click: trigger skill if a skill is selected, else select sprite.
			if (playerSkillState().getSelectedSkill().isPresent()) {
				playerSkillState().getSelectedSkill().ifPresent((skill) -> {
					bus.emit(new ConfirmSkillEvent(ConfirmSkillEvent.CONFIRM, skill));
				});
			} else {
				Sprite selected = selectionChecker.getSelection(model, e.getPos());
				playerSelectionState().setSelectedSprite(selected);
			}
		});
		bus.on(MouseEvent.RIGHT, e -> {
			// right click: cancel selected skill if present. And let the sprite move (or move & attack).
			playerSkillState().getSelectedSkill().ifPresent((skill) -> {
				bus.emit(new SelectSkillEvent(SelectSkillEvent.DESELECT, skill));
			});
			if (playerSelectionState().getSelectionType() == SelectionType.SINGLE) {
				Sprite selected = playerSelectionState().getSelectedSprite();
				ActionMode actionMode = playerInputState().isKeyPressed(KeyCode.SHIFT) ? ActionMode.QUEUE
						: ActionMode.INSTANT;
				bus.emit(new MoveSpriteEvent(MoveSpriteEvent.RAW, actionMode, Player.DEFAULT, selected,
						new Target(e.getPos())));
			}
		});
	}

}
