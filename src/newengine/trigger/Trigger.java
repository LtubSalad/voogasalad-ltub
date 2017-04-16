package newengine.trigger;

import java.util.List;
import java.util.Optional;

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
	
	public String getEvent() {
		return null;
	}
	public List<Condition> getConditions() {
		return conditions;
	}
	public List<String> getActions() {
		return null;
	}
	public Optional<SpriteID> getSpriteID() {// OPTIONAL
		return null;
	}
	
	
}
