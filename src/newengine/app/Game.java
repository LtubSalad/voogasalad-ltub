package newengine.app;

import bus.BasicEventBus;
import bus.EventBus;
import javafx.scene.Scene;
import newengine.events.MapInitializationEvent;
import newengine.gameloop.FXGameLoop;
import newengine.gameloop.GameLoop;
import newengine.managers.collision.CollisionManager;
import newengine.managers.debug.DebugManager;
import newengine.managers.input.InputManager;
import newengine.managers.range.RangeManager;
import newengine.managers.sound.SoundManager;
import newengine.model.PlayerStatsModel;
import newengine.model.SelectionModel;
import newengine.model.SpriteModel;
import newengine.trigger.TriggerManager;
import newengine.utils.variable.VarKey;
import newengine.utils.variable.VarMap;
import newengine.utils.variable.VarValue;
import newengine.view.View;
import newengine.view.camera.Camera;

public class Game {

	private EventBus bus = new BasicEventBus();
	private GameLoop gameLoop;
	private View view;
	private boolean mapInitialized = false;
	
	public Game() {
		SpriteModel spriteModel = new SpriteModel(bus);
		PlayerStatsModel playerStatsModel = new PlayerStatsModel(bus);
		SelectionModel selectionModel = new SelectionModel(bus);
		
		Camera camera = new Camera(bus);
		view = new View(bus, camera);
		
		gameLoop = new FXGameLoop(bus);
		
		CollisionManager collisionManager = new CollisionManager(bus);
		RangeManager rangeManager = new RangeManager(bus);
		
		gameLoop.addLoopComponent((dt) -> collisionManager.checkCollisions(spriteModel.getSprites()));
		gameLoop.addLoopComponent((dt) -> rangeManager.checkRanges(spriteModel.getSprites()));
		gameLoop.addLoopComponent((dt) -> spriteModel.update(dt));
		gameLoop.addLoopComponent((dt) -> view.render(spriteModel));
		gameLoop.addLoopComponent((dt) -> view.render(playerStatsModel));
		gameLoop.addLoopComponent((dt) -> view.render(selectionModel));
		
		InputManager inputManager = new InputManager(bus, camera, spriteModel, playerStatsModel, selectionModel);
		SoundManager soundManager = new SoundManager(bus);
		DebugManager debugManager = new DebugManager(bus);
		
		TriggerManager triggerManager = new TriggerManager(bus, spriteModel);
	}
	
	public EventBus getBus() {
		return bus;
	}
	
	public void start() {
		if (!mapInitialized) {
			bus.emit(new MapInitializationEvent());
			mapInitialized = true;
		}
		gameLoop.start();
	}
	public void pause() {
		gameLoop.pause();
	}
	public Scene getScene() {
		return view.getScene();
	}
	
}
