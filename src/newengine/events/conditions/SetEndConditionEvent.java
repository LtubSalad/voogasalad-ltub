package newengine.events.conditions;

import bus.BusEvent;
import bus.BusEventType;
import newengine.events.timer.DelayedEvent;
import newengine.managers.conditions.ICondition;

public class SetEndConditionEvent extends BusEvent{
	public static final BusEventType<SetEndConditionEvent> SETWIN = new BusEventType<>(DelayedEvent.class.getName() + "SETWIN");
	public static final BusEventType<SetEndConditionEvent> SETLOSE = new BusEventType<>(DelayedEvent.class.getName() + "SETLOSE");
	private ICondition condition;
	
	public SetEndConditionEvent(BusEventType<? extends BusEvent> busEventType, ICondition condition) {
		super(busEventType);
		this.condition = condition;
	}

	public ICondition getCondition(){
		return condition;
	}
}
