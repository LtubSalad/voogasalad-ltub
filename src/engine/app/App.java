package engine.app;

import bus.EventBus;
import engine.gameloop.GameLoop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	EventBus bus;
	
	@Override
	public void start(Stage stage) throws Exception {
		GameFactory gameFactory = new GameFactory();
		Scene scene = gameFactory.createGameScene();
		
		GameLoop gameLoop = gameFactory.createGameLoop();
		gameLoop.start(); // nothing added in the loop yet.
		
		gameFactory.createSoundManager(); // Automatically linked by the event bus.
		
        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
