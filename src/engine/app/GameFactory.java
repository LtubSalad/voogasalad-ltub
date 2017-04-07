package engine.app;

import bus.BasicEventBus;
import bus.EventBus;
import engine.gameloop.FXGameLoop;
import engine.gameloop.GameLoop;
import engine.input.ActionManager;
import engine.input.InputManager;
import engine.model.BasicModel;
import engine.model.Model;
import engine.playerstate.PlayerInputState;
import engine.playerstate.PlayerSelectionState;
import engine.sound.FXSoundManager;
import engine.sound.SoundManager;
import engine.sprite.collision.CollisionChecker;
import engine.sprite.collision.CollisionManager;
import engine.sprite.range.InRangeChecker;
import engine.sprite.range.InRangeManager;
import engine.view.FXView;
import engine.view.View;

public class GameFactory {

	private final EventBus bus;
	
	/**
	 * Each {@link GameFactory} has a unique {@link EventBus}.
	 */
	public GameFactory() {
		bus = new BasicEventBus();
	}
	
	public Model createModel() {
		return new BasicModel();
	}
	
	public View createView() {
		return new FXView(bus);
	}
	
	public GameLoop createGameLoop() {
		return new FXGameLoop();
	}
	
	public SoundManager createSoundManager() {
		return new FXSoundManager(bus);
	}
	
	public InputManager createInputManager(Model model) {
		return new InputManager(bus, model);
	}
	
	public ActionManager createActionManager() {
		return new ActionManager(bus);
	}
	
	public PlayerInputState createPlayerInputState() {
		return new PlayerInputState(bus);
	}
	
	public PlayerSelectionState createPlayerSelectionState() {
		return new PlayerSelectionState(bus);
	}
	
	public CollisionChecker createCollisionChecker() {
		return new CollisionChecker(bus);
	}
	
	public CollisionManager createCollisionManager() {
		return new CollisionManager(bus);
	}
	
	public InRangeChecker createInRangeChecker() {
		return new InRangeChecker(bus);
	}
	
	public InRangeManager createInRangeManager() {
		return new InRangeManager(bus);
	}
	
	
}
