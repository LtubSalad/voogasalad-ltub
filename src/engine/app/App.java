package engine.app;


import engine.camera.Camera;
import engine.camera.GamePoint;
import engine.gameloop.GameLoop;
import engine.model.Model;
import engine.model.PlayerLocalModel;
import engine.player.Player;
import engine.sprite.Movable;
import engine.sprite.Sprite;
import engine.sprite.collision.Collidable;
import engine.sprite.collision.CollisionBound;
import engine.sprite.images.ImageSet;
import engine.sprite.images.LtubImage;
import engine.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		
		GameFactory gameFactory = new GameFactory();
		
		// sprite1
		Sprite sprite1 = new Sprite();
		sprite1.setPos(new GamePoint(100, 100));
		LtubImage image1 = new LtubImage("images/characters/bahamut_left.png");
		ImageSet imageSet1 = new ImageSet();
		imageSet1.setImage(image1);
		sprite1.setImageSet(imageSet1);
		Movable movable1 = new Movable(sprite1);
		sprite1.setMovable(movable1);
		sprite1.setCollidable(new Collidable(new CollisionBound(image1)));
//		sprite1.setImageOffset(new Point(30, 30));
		sprite1.setDetectionRange(128);
		
		// sprite2
		Sprite sprite2 = new Sprite();
		sprite2.setPos(new GamePoint(200, 100));
		LtubImage image2 = new LtubImage("images/characters/bahamut_right.png");
		ImageSet imageSet2 = new ImageSet();
		imageSet2.setImage(image2);
		sprite2.setImageSet(imageSet2);
		Movable movable2 = new Movable(sprite2);
		movable2.setSpeed(100);
		sprite2.setCollidable(new Collidable(new CollisionBound(image2)));
		sprite2.setMovable(movable2);
		sprite2.setDetectionRange(256);
		
		// game player (user)
		Player player = new Player("Player 1");
		
		// model and view
		Model model = gameFactory.createModel(player);
		model.addSprite(sprite1);
		model.addSprite(sprite2);
		PlayerLocalModel localModel = gameFactory.createPlayerLocalModel();
		Camera camera = gameFactory.createCamera();
		View view = gameFactory.createView(camera);

		
		// game loop 
		GameLoop gameLoop = gameFactory.createGameLoop();
//		CollisionChecker collisionChecker = gameFactory.createCollisionChecker();
//		gameLoop.addLoopComponent((dt) -> collisionChecker.checkCollision(model.getSprites()));
//		InRangeChecker inRangeChecker = gameFactory.createInRangeChecker();
//		gameLoop.addLoopComponent((dt) -> inRangeChecker.checkInRange(model.getSprites()));
		gameLoop.addLoopComponent((dt) -> model.update(dt));
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
