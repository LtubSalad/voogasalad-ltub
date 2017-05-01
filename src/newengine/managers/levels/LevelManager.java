package newengine.managers.levels;

import java.util.List;

import bus.EventBus;
import gamecreation.level.ILevelData;
import newengine.events.conditions.EndConditionTriggeredEvent;
import newengine.events.conditions.SetEndConditionEvent;
import newengine.events.levels.InitILevelsEvent;
import newengine.events.levels.SetLevelEvent;
import newengine.events.levels.WinGameEvent;
import newengine.events.spawner.SpawnPrefEvent;
import newengine.managers.conditions.GoldMinimumCondition;
import newengine.managers.conditions.NoLivesCondition;

public class LevelManager{
	private EventBus bus;
	private List<ILevelData> data;
	private int numLevels;
	private int currentLevel;
	
	public LevelManager(EventBus bus){
		this.bus = bus;
		initHandlers();
	}
	
	private void initLevels(List<ILevelData> levelDataList) {
		this.data = levelDataList;
		this.currentLevel = 1;
		if(data != null){
			this.numLevels = data.size();
			loadLevel(data.get(0));
		}
	}
	
	private void initHandlers() {
		bus.on(InitILevelsEvent.ANY, e -> {
			initLevels(e.getLevelDataList());
		});
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
	
	private void loadLevel(ILevelData newLevel){
		bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETWIN, new GoldMinimumCondition(1000)));//newLevel.getWinningCondition()));
		bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETLOSE, new NoLivesCondition()));//newLevel.getLosingCondition()));
		bus.emit(new SpawnPrefEvent(SpawnPrefEvent.SETPREFS, newLevel.getSpawnTime()));	
		
	}

}
