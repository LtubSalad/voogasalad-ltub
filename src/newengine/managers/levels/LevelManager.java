package newengine.managers.levels;

import java.util.List;

import bus.EventBus;
import gamecreation.level.LevelData;
import newengine.events.conditions.EndConditionTriggeredEvent;
import newengine.events.conditions.SetEndConditionEvent;

public class LevelManager{
	private EventBus bus;
	private List<LevelData> data;
	private int numLevels;
	private int currentLevel;
	
	public LevelManager(EventBus bus, List<LevelData> data){
		this.bus = bus;
		this.data = data;
		this.numLevels = data.size();
		this.currentLevel = 1;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(EndConditionTriggeredEvent.WIN, e -> nextLevel());
		bus.on(SetLevelEvent.SET, e -> setLevel(e.getLevelNumber()));
	}

	public void nextLevel(){
		if(!(currentLevel == numLevels)){
			loadLevel(data.get(currentLevel-1));
			currentLevel++;
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
	}

}
