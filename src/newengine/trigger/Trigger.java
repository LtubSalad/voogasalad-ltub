package newengine.trigger;

import java.util.ArrayList;
import java.util.List;

public class Trigger {

	private TriggerEvent triggerEvent;
	private List<TriggerCondition> triggerConditions = new ArrayList<>();
	private List<TriggerAction> triggerActions = new ArrayList<>();
	
	public Trigger(TriggerEvent eventType) {
		this.triggerEvent = eventType;
	}
	public void addCondition(TriggerCondition triggerCondition) {
		triggerConditions.add(triggerCondition);
	}
	public void addAction(TriggerAction triggerAction) {
		triggerActions.add(triggerAction);
	}
	
	public TriggerEvent getEvent() {
		return triggerEvent;
	}
	public List<TriggerCondition> getConditions() {
		return triggerConditions;
	}
	public List<TriggerAction> getActions() {
		return triggerActions;
	}

	
}
