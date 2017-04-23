package newengine.trigger;

import bus.BusEvent;
import newengine.model.Models;

public interface TriggerCondition {
	
	public boolean isTrue(Models models, BusEvent event);

}
