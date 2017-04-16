package newengine.input;

import bus.EventBus;
import engine.model.Model;
import javafx.scene.input.KeyCode;
import newengine.event.input.GameWorldMouseEvent;
import newengine.event.input.KeyEvent;
import newengine.event.input.MouseEvent;
import newengine.model.SpriteModel;
import newengine.player.Player;
import newengine.sprite.Sprite;
import newengine.utils.ActionMode;
import newengine.utils.Target;
import newengine.utils.checker.SelectionChecker;
import newengine.utils.point.GamePoint;
import newengine.utils.point.ViewPoint;
import newengine.view.camera.Camera;

public class InputManager {

	private EventBus bus;
	private SpriteModel model;
	private Camera camera;
	private KeyInputState keyInputState;
	
	public InputManager(EventBus bus, SpriteModel model, Camera camera) {
		this.bus = bus;
		this.model = model;
		this.camera = camera;
		keyInputState = new KeyInputState(bus);
		initHandlers();
	}

	private Target target(ViewPoint viewPoint) {
		GamePoint gamePoint = camera.viewToGame(viewPoint);
		Sprite selected = SelectionChecker.getSelection(model.getSprites(), gamePoint);
		return new Target(gamePoint, selected);
	}
	private ActionMode actionMode() {
		ActionMode actionMode = keyInputState.isKeyPressed(KeyCode.SHIFT) ? ActionMode.QUEUE
				: ActionMode.INSTANT;
		return actionMode;
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
