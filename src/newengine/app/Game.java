package newengine.app;

import bus.BasicEventBus;
import bus.EventBus;
import javafx.scene.Scene;
import newengine.events.GameInitializationEvent;
import newengine.gameloop.FXGameLoop;
import newengine.gameloop.GameLoop;
import newengine.managers.collision.CollisionManager;
import newengine.managers.conditions.ConditionManager;
import newengine.managers.debug.DebugManager;
import newengine.managers.input.InputManager;
import newengine.managers.range.RangeManager;
import newengine.managers.sound.SoundManager;
import newengine.model.PlayerStatsModel;
import newengine.model.SelectionModel;
import newengine.model.SpriteModel;
import newengine.sprite.player.Player;
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
		
		// if reading in data from an XML file, will be passed the sprite model + player stats model 
		SpriteModel spriteModel = new SpriteModel(bus);
		PlayerStatsModel playerStatsModel = new PlayerStatsModel(bus, Player.MAIN_PLAYER); // TODO CONNECT PLAYER AND PLAYERSTATSMODEL
		SelectionModel selectionModel = new SelectionModel(bus);
		initializeGame(spriteModel, playerStatsModel, selectionModel);
		
	}
	
	
	
	
	/**
	 * @param spriteModel - model of sprites read in from XML file
	 * @param playerModel - model of players instantiated by XML file
	 * 	  
	 * constructor allowing the game to be instantiated with sprite/player data from an XML file
	 * 
	 */
	public Game(SpriteModel spriteModel, PlayerStatsModel playerModel){
		SelectionModel selectionModel = new SelectionModel(bus); // TODO: (tahia)  remove duplicate line 
		initializeGame(spriteModel, playerModel, selectionModel);
		
	}
	
	/**
	 * constructor used by data processors to instantiate a game based on authoring environment data 
	 * 
	 * @param gameSprites
	 * @param bus2
	 */
	public Game(SpriteModel gameSprites, BasicEventBus bus2) {
		bus = bus2; 
		SelectionModel selectionModel = new SelectionModel(bus);
		PlayerStatsModel playerStatsModel = new PlayerStatsModel(bus);
		initializeGame(gameSprites, playerStatsModel, selectionModel); 
	}




	private void initializeGame(SpriteModel spriteModel, PlayerStatsModel playerStatsModel, SelectionModel selectionModel){
		Camera camera = new Camera(bus);
		view = new View(bus, camera);
		
		gameLoop = new FXGameLoop(bus);
		
		CollisionManager collisionManager = new CollisionManager(bus); // TODO
		RangeManager rangeManager = new RangeManager(bus); // TODO

		InputManager inputManager = new InputManager(bus, spriteModel, playerStatsModel, selectionModel);
		SoundManager soundManager = new SoundManager(bus);
		DebugManager debugManager = new DebugManager(bus);
		TriggerManager triggerManager = new TriggerManager(bus);
		ConditionManager conditionManager = new ConditionManager(bus,spriteModel, playerStatsModel);
		
		gameLoop.addLoopComponent((dt) -> collisionManager.checkCollisions(spriteModel.getSprites()));
		gameLoop.addLoopComponent((dt) -> rangeManager.checkRanges(spriteModel.getSprites()));
		gameLoop.addLoopComponent((dt) -> spriteModel.update(dt));
		gameLoop.addLoopComponent((dt) -> view.render(spriteModel));
		gameLoop.addLoopComponent((dt) -> view.render(playerStatsModel));
		gameLoop.addLoopComponent((dt) -> view.render(selectionModel));
		gameLoop.addLoopComponent((dt) -> conditionManager.checkConditions());
		
		
	}
	
	public EventBus getBus() {
		return bus;
	}
	
	public void start() {
		if (!mapInitialized) {
			bus.emit(new GameInitializationEvent());
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
