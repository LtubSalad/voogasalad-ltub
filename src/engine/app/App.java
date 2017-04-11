package engine.app;


import engine.camera.Camera;
import engine.camera.GamePoint;
import engine.gameloop.GameLoop;
import engine.model.Model;
import engine.model.PlayerLocalModel;
import engine.player.Player;
import engine.sprite.Sprite;
import engine.sprite.collision.Collidable;
import engine.sprite.collision.CollisionBound;
import engine.sprite.collision.CollisionChecker;
import engine.sprite.images.ImageSet;
import engine.sprite.images.LtubImage;
import engine.sprite.movable.Movable;
import engine.sprite.range.InRangeChecker;
import engine.spritecreation.SpriteBuildingManager;
import engine.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		
		GameFactory gameFactory = new GameFactory();
	
		// game player (user)
		Player player = new Player("Player 1");
		
		// model and view
		Model model = gameFactory.createModel(player);
		PlayerLocalModel localModel = gameFactory.createPlayerLocalModel();
		Camera camera = gameFactory.createCamera();
		View view = gameFactory.createView(camera);
		
		//sprite with attributes creator
		SpriteBuildingManager spriteBuildingManager = gameFactory.createSpriteBuildingManager();
		
		// game loop 
		GameLoop gameLoop = gameFactory.createGameLoop();
		CollisionChecker collisionChecker = gameFactory.createCollisionChecker();
		InRangeChecker inRangeChecker = gameFactory.createInRangeChecker();
		
		/*
		 * move all the monsters forward
		 * check each tower for monsters in its range (and fire weapons if necessary)
		 * move all the bullets 
		 * check if any bullets have hit its target (and decrement health)
		 * remove any monsters that have 0 or negative health
		 */
		gameLoop.addLoopComponent((dt) -> model.updatePositions(dt)); //updates any sprite with movable attribute including weapons
		gameLoop.addLoopComponent((dt) -> inRangeChecker.checkInRange(model.getSprites(), 1, 2)); //TODO which teams?
		gameLoop.addLoopComponent((dt) -> collisionChecker.checkWeaponCollision(model.getSprites()));
		
		
		gameLoop.addLoopComponent((dt) -> view.render(model));
		gameLoop.addLoopComponent((dt) -> view.render(localModel));
		
		
		// create other event managers (they are automatically linked by the bus)
		gameFactory.createCollisionManager();
		gameFactory.createInRangeManager();
		gameFactory.createSoundManager();
		gameFactory.createInputManager(model, camera);
		gameFactory.createActionFilter();
		gameFactory.createActionManager();
		gameFactory.createSkillManager();
		
		
		// set scene, start loop, and show stage
		Scene scene = view.getScene();
		gameLoop.start();
        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
}