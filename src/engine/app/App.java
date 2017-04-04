package engine.app;

import engine.gameloop.GameLoop;
import engine.model.BasicModel;
import engine.model.Model;
import engine.sprite.Image;
import engine.sprite.Movable;
import engine.sprite.Sprite;
import engine.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	
	@Override
	public void start(Stage stage) throws Exception {
		GameFactory gameFactory = new GameFactory();
		
		Model model = new BasicModel();
		View view = gameFactory.createView();
		Sprite sprite1 = new Sprite();
		sprite1.setPos(100, 100);
		sprite1.setImage(new Image("images/characters/bahamut_left.png"));
		Movable movable1 = new Movable(sprite1);
		sprite1.setMovable(movable1);
		model.addSprite(sprite1);
		
		Scene scene = view.getScene();
		
		GameLoop gameLoop = gameFactory.createGameLoop();
		gameLoop.addLoopComponent(model.getLoopComponent());
		gameLoop.addLoopComponent((dt) -> view.render(model));
		gameLoop.start(); // nothing added in the loop yet.
		
		gameFactory.createSoundManager(); // Automatically linked by the event bus.
		gameFactory.createInputManager(model); 
		
        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
