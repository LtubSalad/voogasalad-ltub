package engine.app;

import java.io.File;

import bus.BasicEventBus;
import bus.EventBus;
import engine.action.ActionFilter;
import engine.action.ActionManager;
import engine.camera.Camera;
import engine.gameloop.FXGameLoop;
import engine.gameloop.GameLoop;
import engine.input.InputManager;
import engine.model.BasicModel;
import engine.model.Model;
import engine.model.PlayerLocalModel;
import engine.player.Player;
import engine.playerstate.PlayerSelectionState;
import engine.playerstate.PlayerSkillState;
import engine.skill.SkillManager;
import engine.sound.FXSoundManager;
import engine.sound.SoundManager;
import engine.sprite.attacker.AttackManager;
import engine.sprite.collidable.CollisionChecker;
import engine.sprite.collidable.CollisionManager;
import engine.sprite.range.InRangeChecker;
import engine.sprite.range.InRangeManager;
import engine.spritecreation.GameBuildingManager;
import engine.view.FXView;
import engine.view.View;

public class GameFactory {

	private final EventBus bus;
	private File gameFile; 
	
	/**
	 * Each {@link GameFactory} has a unique {@link EventBus}.
	 */
	public GameFactory() {
		bus = new BasicEventBus();
	}
	
	public Model createModel(Player player) {
		return new BasicModel(bus, player);
	}
	
	public View createView(Camera camera) {
		return new FXView(bus, camera);
	}
	
	public GameLoop createGameLoop() {
		return new FXGameLoop();
	}
	
	public Camera createCamera() {
		return new Camera();
	}
	
	public SoundManager createSoundManager() {
		return new FXSoundManager(bus);
	}
	
	public InputManager createInputManager(Model model, Camera camera) {
		return new InputManager(bus, model, camera);
	}

	public ActionFilter createActionFilter() {
		return new ActionFilter(bus);
	}
	public ActionManager createActionManager() {
		return new ActionManager(bus);
	}
	

	public CollisionChecker createCollisionChecker() {
		return new CollisionChecker(bus);
	}
	
	public CollisionManager createCollisionManager() {
		return new CollisionManager(bus);
	}
	
	public InRangeChecker createInRangeChecker() {
		return new InRangeChecker(bus);
	}
	
	public InRangeManager createInRangeManager() {
		return new InRangeManager(bus);
	}

	public AttackManager createAttackManager() {
		return new AttackManager(bus);
	}
	
	private PlayerSelectionState createPlayerSelectionState() {
		return new PlayerSelectionState(bus);
	}
	
	private PlayerSkillState createPlayerSkillState() {
		return new PlayerSkillState(bus);
	}
	
	public PlayerLocalModel createPlayerLocalModel() {
		PlayerLocalModel localModel = new PlayerLocalModel(bus);
		localModel.setPlayerSelectionState(createPlayerSelectionState());
		localModel.setPlayerSkillState(createPlayerSkillState());
		return localModel;
	}
	
	public SkillManager createSkillManager() {
		return new SkillManager(bus);
	}
	
	public GameBuildingManager createGameBuildingManager(){
		return new GameBuildingManager(bus);
	}

	public EventBus getBus() {
		return bus;
	}

		
	public void loadGame(File file){
		this.gameFile = file;  
	}
	
	public File getFile(){
		return gameFile; 
	}
}
