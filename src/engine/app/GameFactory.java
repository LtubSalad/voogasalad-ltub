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
	
//	public Scene createGameScene() {
//        VBox root = new VBox();
//        Button soundBtn = new Button("music");
//        soundBtn.setOnAction((e) -> {
//        	bus.emit(new SoundEvent("data/sounds/01-dark-covenant.mp3"));
//        });
//        root.getChildren().add(soundBtn);
//        Scene scene = new Scene(root, 400, 300);
//        return scene;
//	}
	
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
