package newengine.managers.input;

import bus.EventBus;
import commons.point.GamePoint;
import commons.point.ViewPoint;
import javafx.scene.input.KeyCode;
import newengine.events.input.GameWorldMouseEvent;
import newengine.events.input.KeyEvent;
import newengine.events.input.MouseEvent;
import newengine.model.PlayerStatsModel;
import newengine.model.SelectionModel;
import newengine.model.SpriteModel;
import newengine.sprite.Sprite;
import newengine.sprite.player.Player;
import newengine.utils.ActionMode;
import newengine.utils.Target;
import newengine.utils.checker.SelectionChecker;
import newengine.view.camera.Camera;

public class InputManager {

	private EventBus bus;
	private SpriteModel spriteModel;
	private PlayerStatsModel playerStatsModel;
	private SelectionModel selectionModel;
	private Camera camera;
	private KeyInputState keyInputState;
	
	public InputManager(EventBus bus, Camera camera, SpriteModel spriteModel,
			PlayerStatsModel playerStatsModel, SelectionModel selectionModel) {
		this.bus = bus;
		this.spriteModel = spriteModel;
		this.playerStatsModel = playerStatsModel;
		this.selectionModel = selectionModel;
		this.camera = camera;
		keyInputState = new KeyInputState(bus);
		initHandlers();
	}

	private Target target(ViewPoint viewPoint) {
		GamePoint gamePoint = camera.viewToGame(viewPoint);
		Sprite selected = SelectionChecker.getSelection(spriteModel.getSprites(), gamePoint);
		return new Target(gamePoint, selected);
	}
	private ActionMode actionMode() {
		ActionMode actionMode = keyInputState.isKeyPressed(KeyCode.SHIFT) ? ActionMode.QUEUE
				: ActionMode.INSTANT;
		return actionMode;
	}
	
	private Player player() { // TODO
		return Player.DEFAULT;
	}

	private void initHandlers() {
		bus.on(KeyEvent.PRESS, e -> {
			keyInputState.pressKey(e.getCode());
		});
		bus.on(KeyEvent.RELEASE, e -> {
			keyInputState.releaseKey(e.getCode());
		});
		bus.on(MouseEvent.LEFT, e -> {
			// left click: trigger skill if a skill is selected, else select sprite.
			bus.emit(new GameWorldMouseEvent(GameWorldMouseEvent.CONFIRM_SKILL, target(e.getPos()), actionMode(), player()));
		});
		bus.on(MouseEvent.RIGHT, e -> {
			// right click: cancel selected skill if present. And let the sprite move (or move & attack).
			bus.emit(new GameWorldMouseEvent(GameWorldMouseEvent.CANCEL_SKILL_AND_MOVE_SPRITE, target(e.getPos()), actionMode(), player()));
		});
	}

}
