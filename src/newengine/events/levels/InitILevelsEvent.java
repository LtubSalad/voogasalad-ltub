package newengine.events.levels;

import java.util.List;

import bus.BusEvent;
import bus.BusEventType;
import gamecreation.level.ILevelData;

public class InitILevelsEvent extends BusEvent {

	public static final BusEventType<InitILevelsEvent> ANY = new BusEventType<>(InitILevelsEvent.class.getName()+"ANY");
	
	private List<ILevelData> levelDataList;
	
	public InitILevelsEvent(List<ILevelData> levelDataList) {
		super(ANY);
		this.levelDataList = levelDataList;
	}

	public List<ILevelData> getLevelDataList() {
		return levelDataList;
	}
	
}
