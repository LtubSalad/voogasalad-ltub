package newengine.app;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;
import newengine.events.SpriteModelEvent;
import newengine.events.game.GameLoadEvent;
import newengine.events.game.GameSaveEvent;
import newengine.events.input.KeyEvent;
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
		triggers.add(gameSave());
		triggers.add(gameLoad());
		triggers.add(printWhenSpriteMoves());
		triggers.add(playSoundWhenFireProjectile());
		triggers.add(playSoundWhenHealthChanges());
		triggers.add(playSoundWhenNewSpriteAdded());
//		triggers.add(loseHealthWhenMove());
	}
	
	private Trigger gameSave() {
		TriggerEvent triggerEvent = TriggerEvent.createGameTriggerEvent(KeyEvent.PRESS);
		TriggerAction triggerAction = TriggerAction.createGameTriggerAction(
				new GameSaveEvent());
		Trigger trigger = new Trigger(triggerEvent);
		trigger.addAction(triggerAction);
		trigger.addCondition((models, event) -> {
			if (event instanceof KeyEvent) {
				return ((KeyEvent) event).getCode().equals(KeyCode.S);
			} else {
				return false;
			}
		});
		return trigger;
	}
	
	private Trigger gameLoad() {
		TriggerEvent triggerEvent = TriggerEvent.createGameTriggerEvent(KeyEvent.PRESS);
		TriggerAction triggerAction = TriggerAction.createGameTriggerAction(
				new GameLoadEvent());
		Trigger trigger = new Trigger(triggerEvent);
		trigger.addAction(triggerAction);
		trigger.addCondition((models, event) -> {
			if (event instanceof KeyEvent) {
				return ((KeyEvent) event).getCode().equals(KeyCode.L);
			} else {
				return false;
			}
		});
		return trigger;
	}
	
	private Trigger printWhenSpriteMoves() {
		TriggerEvent triggerEvent = TriggerEvent.createSpriteAllTriggerEvent(MoveEvent.START_POSITION);
		//TriggerAction triggerAction = TriggerAction.createSpriteTriggeringUnitTriggerAction(
		//		new ChangeHealthEvent(ChangeHealthEvent.ANY, -20));
		Trigger trigger = new Trigger(triggerEvent);
		//trigger.addAction(triggerAction);
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
