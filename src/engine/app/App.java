package engine.app;

import commons.Point;
import engine.gameloop.GameLoop;
import engine.input.ActionManager;
import engine.input.InputManager;
import engine.model.Model;
import engine.playerstate.PlayerInputState;
import engine.sprite.LtubImage;
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
		
		Model model = gameFactory.createModel();
		View view = gameFactory.createView();
		// sprite1
		Sprite sprite1 = new Sprite();
		sprite1.setPos(new Point(100, 100));
		sprite1.setImage(new LtubImage("images/characters/bahamut_left.png"));
		Movable movable1 = new Movable(sprite1);
		sprite1.setMovable(movable1);
//		sprite1.setImageOffset(new Point(30, 30));
		model.addSprite(sprite1);
		// sprite2
		Sprite sprite2 = new Sprite();
		sprite2.setPos(new Point(200, 100));
		sprite2.setImage(new LtubImage("images/characters/bahamut_right.png"));
		Movable movable2 = new Movable(sprite2);
		movable2.setSpeed(100);
		sprite2.setMovable(movable2);
		model.addSprite(sprite2);
		
		model.setPlayerSelectionState(gameFactory.createPlayerSelectionState());
		
		Scene scene = view.getScene();
		
		GameLoop gameLoop = gameFactory.createGameLoop();
		gameLoop.addLoopComponent(model.getLoopComponent());
		gameLoop.addLoopComponent((dt) -> view.render(model));
		gameLoop.start(); // nothing added in the loop yet.
		
		gameFactory.createSoundManager(); // Automatically linked by the event bus.
		InputManager inputManager = gameFactory.createInputManager(model);
		PlayerInputState playerInputState = gameFactory.createPlayerInputState();
		ActionManager actionManager = gameFactory.createActionManager();
		inputManager.setPlayerInputState(playerInputState);
		inputManager.setActionManager(actionManager);
		inputManager.initHandlers();
		
        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
