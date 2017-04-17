package newengine.trigger;

import java.util.List;
import java.util.Optional;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.SpriteID;

public class Trigger {

	private BusEventType eventType;
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
	
	public BusEventType getEvent() {
		return eventType;
	}
	public List<Condition> getConditions() {
		return conditions;
	}
	public List<Action> getActions() {
		return actions;
	}
	public Optional<SpriteID> getSpriteID() {// TODO OPTIONAL
		return null;
	}
	
	
}
