package newengine.trigger;

import java.util.List;

import bus.BusEvent;

public class Trigger {

	private BusEvent event;
	private List<Condition> conditions;
	private List<Action> actions;
	
	public Trigger() {
		
	}
	
	public void setEvent(String event) {
		if (event.equals("GameSave")) {
			
		}
	}
	
	public void addCondition(String condition) {
		
	}
	
	public void addAction(String action) {
		
	}
}
