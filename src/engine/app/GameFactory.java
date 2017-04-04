package engine.app;

import bus.BasicEventBus;
import bus.EventBus;
import engine.gameloop.FXGameLoop;
import engine.gameloop.GameLoop;
import engine.input.InputManager;
import engine.model.Model;
import engine.sound.FXSoundManager;
import engine.sound.SoundManager;
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
	
}
