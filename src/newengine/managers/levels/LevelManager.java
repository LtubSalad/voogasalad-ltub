package newengine.managers.levels;

import java.util.List;

import bus.EventBus;
import gamecreation.level.LevelData;
import newengine.events.conditions.EndConditionTriggeredEvent;
import newengine.events.conditions.SetEndConditionEvent;
import newengine.events.spawner.SpawnPrefEvent;

public class LevelManager{
	private EventBus bus;
	private List<LevelData> data;
	private int numLevels;
	private int currentLevel;
	
	public LevelManager(EventBus bus, List<LevelData> data){
		this.bus = bus;
		this.data = data;
		this.currentLevel = 1;
		if(data != null){
			this.numLevels = data.size();
			loadLevel(data.get(0));
		}
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(EndConditionTriggeredEvent.WIN, e -> {if(data!= null) nextLevel();});
		bus.on(EndConditionTriggeredEvent.LOSE, e -> System.out.println("loser"));
		bus.on(SetLevelEvent.SET, e -> {if(data!= null) setLevel(e.getLevelNumber());});
	}

	public void nextLevel(){
		if(!(currentLevel == numLevels)){
			currentLevel++;
			System.out.println("Current level: " + currentLevel);
			loadLevel(data.get(currentLevel-1));
			return;
		}
			bus.emit(new WinGameEvent(WinGameEvent.WIN));
	}
	
	public void resetLevel(){
		loadLevel(data.get(currentLevel-1));
	}
	
	public void setLevel(int levelNumber){
		if(levelNumber > 0 && levelNumber <= numLevels){
			loadLevel(data.get(levelNumber-1));
		}
	}
	
	private void loadLevel(LevelData newLevel){
		bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETWIN, newLevel.getWinningCondition()));
		bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETLOSE, newLevel.getLosingCondition()));
		bus.emit(new SpawnPrefEvent(SpawnPrefEvent.SETPREFS, newLevel.getSpawnTime()));	
	}

}
