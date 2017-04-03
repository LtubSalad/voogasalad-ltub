package engine.app;

import bus.BasicEventBus;
import bus.EventBus;
import engine.gameloop.FXGameLoop;
import engine.gameloop.GameLoop;
import engine.sound.FXSoundManager;
import engine.sound.SoundEvent;
import engine.sound.SoundManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GameFactory {

	private final EventBus bus;
	
	/**
	 * Each {@link GameFactory} has a unique {@link EventBus}.
	 */
	public GameFactory() {
		bus = new BasicEventBus();
	}
	
	public Scene createGameScene() {
        VBox root = new VBox();
        Button soundBtn = new Button("music");
        soundBtn.setOnAction((e) -> {
        	bus.emit(new SoundEvent("data/sounds/01-dark-covenant.mp3"));
        });
        root.getChildren().add(soundBtn);
        Scene scene = new Scene(root, 400, 300);
        return scene;
	}
	
	public GameLoop createGameLoop() {
		return new FXGameLoop();
	}
	
	public SoundManager createSoundManager() {
		return new FXSoundManager(bus);
	}
	
}
