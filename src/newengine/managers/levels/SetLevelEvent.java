package newengine.managers.levels;

import bus.BusEvent;
import bus.BusEventType;

public class SetLevelEvent extends BusEvent {
	private int levelNum;
	public static final BusEventType<SetLevelEvent> SET = new BusEventType<>(SetLevelEvent.class.getName() + "SET");
	
	public SetLevelEvent(BusEventType<? extends BusEvent> busEventType, int levelNumber) {
		super(busEventType);
		this.levelNum = levelNumber;
	}
	
	public int getLevelNumber(){
		return levelNum;
	}

}
