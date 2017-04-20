package newengine.app;

import java.util.ArrayList;
import java.util.List;

import newengine.events.debug.SysPrintEvent;
import newengine.events.sound.SoundEvent;
import newengine.events.sprite.ChangeHealthEvent;
import newengine.events.sprite.FireProjectileEvent;
import newengine.events.sprite.MoveEvent;
import newengine.trigger.Trigger;
import newengine.trigger.TriggerAction;
import newengine.trigger.TriggerAction.TriggerActionType;
import newengine.trigger.TriggerEvent;
import newengine.trigger.TriggerEvent.TriggerEventType;

public class TestTriggers {

	private List<Trigger> triggers = new ArrayList<>();
	
	public TestTriggers() {
		triggers.add(spriteMovePrintTrigger());
		triggers.add(playSoundWhenFireProjectile());
		triggers.add(playSoundWhenHealthChanges());
	}
	
	private Trigger spriteMovePrintTrigger() {
		TriggerEvent triggerEvent = new TriggerEvent(TriggerEventType.SPRITE_ALL, MoveEvent.SPRITE);
		TriggerAction triggerAction = new TriggerAction(TriggerActionType.GAME, 
				new SysPrintEvent("all sprite trigger action is printed"));
		Trigger trigger = new Trigger(triggerEvent);
		trigger.addAction(triggerAction);
		return trigger;
	}
	
	private Trigger playSoundWhenFireProjectile() {
		TriggerEvent triggerEvent = new TriggerEvent(TriggerEventType.SPRITE_ALL, FireProjectileEvent.SPECIFIC);
		TriggerAction triggerAction = new TriggerAction(TriggerActionType.GAME,
				new SoundEvent(SoundEvent.SOUND_EFFECT, "data/sounds/Thunder.wav"));
		Trigger trigger = new Trigger(triggerEvent);
		trigger.addAction(triggerAction);
		return trigger;
	}
	
	private Trigger playSoundWhenHealthChanges() {
		TriggerEvent triggerEvent = new TriggerEvent(TriggerEventType.SPRITE_ALL, ChangeHealthEvent.ANY);
		TriggerAction triggerAction = new TriggerAction(TriggerActionType.GAME,
				new SoundEvent(SoundEvent.SOUND_EFFECT, "data/sounds/Bowhit.wav"));
		Trigger trigger = new Trigger(triggerEvent);
		trigger.addAction(triggerAction);
		return trigger;
	}
	
	public List<Trigger> getTriggers() {
		return triggers;
	}
	
}
