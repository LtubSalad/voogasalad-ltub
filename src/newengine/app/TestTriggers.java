package newengine.app;

import java.util.ArrayList;
import java.util.List;

import newengine.events.SpriteModelEvent;
import newengine.events.debug.SysPrintEvent;
import newengine.events.sound.SoundEvent;
import newengine.events.sprite.ChangeHealthEvent;
import newengine.events.sprite.FireProjectileEvent;
import newengine.events.sprite.MoveEvent;
import newengine.trigger.Trigger;
import newengine.trigger.TriggerAction;
import newengine.trigger.TriggerEvent;

public class TestTriggers {

	private List<Trigger> triggers = new ArrayList<>();
	
	public TestTriggers() {
		triggers.add(printWhenSpriteMoves());
		triggers.add(playSoundWhenFireProjectile());
		triggers.add(playSoundWhenHealthChanges());
		triggers.add(playSoundWhenNewSpriteAdded());
		triggers.add(loseHealthWhenMove());
	}
	
	private Trigger printWhenSpriteMoves() {
		TriggerEvent triggerEvent = TriggerEvent.createSpriteAllTriggerEvent(MoveEvent.START_SPRITE);
		TriggerAction triggerAction = TriggerAction.createGameTriggerAction(
				new SysPrintEvent("all sprite trigger action is printed"));
		Trigger trigger = new Trigger(triggerEvent);
		trigger.addAction(triggerAction);
		return trigger;
	}
	
	private Trigger playSoundWhenFireProjectile() {
		TriggerEvent triggerEvent = TriggerEvent.createSpriteAllTriggerEvent(FireProjectileEvent.SPECIFIC);
		TriggerAction triggerAction = TriggerAction.createGameTriggerAction(
				new SoundEvent(SoundEvent.SOUND_EFFECT, "data/sounds/Thunder.wav"));
		Trigger trigger = new Trigger(triggerEvent);
		trigger.addAction(triggerAction);
		return trigger;
	}
	
	private Trigger playSoundWhenHealthChanges() {
		TriggerEvent triggerEvent = TriggerEvent.createSpriteAllTriggerEvent(ChangeHealthEvent.ANY);
		TriggerAction triggerAction = TriggerAction.createGameTriggerAction(
				new SoundEvent(SoundEvent.SOUND_EFFECT, "data/sounds/Bowhit.wav"));
		Trigger trigger = new Trigger(triggerEvent);
		trigger.addAction(triggerAction);
		return trigger;
	}
	
	private Trigger playSoundWhenNewSpriteAdded() {
		TriggerEvent triggerEvent = TriggerEvent.createGameTriggerEvent(SpriteModelEvent.ADD);
		TriggerAction triggerAction = TriggerAction.createGameTriggerAction(
				new SoundEvent(SoundEvent.SOUND_EFFECT, "data/sounds/Dnpisd1.wav"));
		Trigger trigger = new Trigger(triggerEvent);
		trigger.addAction(triggerAction);
		return trigger;
	}
	
	private Trigger loseHealthWhenMove() {
		TriggerEvent triggerEvent = TriggerEvent.createSpriteAllTriggerEvent(MoveEvent.START_POSITION);
		TriggerAction triggerAction = TriggerAction.createSpriteTriggeringUnitTriggerAction(
				new ChangeHealthEvent(ChangeHealthEvent.ANY, -5));
		Trigger trigger = new Trigger(triggerEvent);
		trigger.addAction(triggerAction);
		return trigger;
	}
	
	public List<Trigger> getTriggers() {
		return triggers;
	}
	
}
