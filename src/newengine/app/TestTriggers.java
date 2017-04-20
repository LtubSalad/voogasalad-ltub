package newengine.app;

import java.util.ArrayList;
import java.util.List;

import newengine.events.debug.SysPrintEvent;
import newengine.events.sprite.MoveEvent;
import newengine.trigger.Trigger;
import newengine.trigger.TriggerAction;
import newengine.trigger.TriggerEvent;
import newengine.trigger.TriggerAction.TriggerActionType;
import newengine.trigger.TriggerEvent.TriggerEventType;

public class TestTriggers {

	private List<Trigger> triggers = new ArrayList<>();
	
	public TestTriggers() {
		triggers.add(spriteMovePrintTrigger());
	}
	
	private Trigger spriteMovePrintTrigger() {
		TriggerEvent triggerEvent = new TriggerEvent(TriggerEventType.SPRITE_ALL, MoveEvent.TYPE);
		TriggerAction triggerAction = new TriggerAction(TriggerActionType.GAME, 
				new SysPrintEvent("all sprite trigger action is printed"));
		Trigger trigger = new Trigger(triggerEvent);
		trigger.addAction(triggerAction);
		return trigger;
	}
	
	public List<Trigger> getTriggers() {
		return triggers;
	}
	
}
