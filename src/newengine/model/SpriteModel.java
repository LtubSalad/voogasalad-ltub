package newengine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import bus.BusEvent;
import bus.EventBus;
import newengine.events.SpriteModelEvent;
import newengine.events.sprite.SetGameBusEvent;
import newengine.events.trigger.SpriteTriggerActionEvent;
import newengine.events.trigger.SpriteTriggerRegisterEvent;
import newengine.sprite.Sprite;
import newengine.sprite.SpriteID;

/**
 * A container for sprites.
 * @author keping
 *
 */
public class SpriteModel {

	private EventBus bus;
	private List<Sprite> sprites = new ArrayList<>();
	private List<Sprite> spritesToAdd = new ArrayList<>();
	private List<Sprite> spritesToRemove = new ArrayList<>();
	private List<SpriteTriggerRegisterEvent> spriteTriggerRegisterEvents = new ArrayList<>();
	
	
	public SpriteModel(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(SpriteModelEvent.ADD, (e) -> {
			addSprite(e.getSprites());
		});
		bus.on(SpriteModelEvent.REMOVE, (e) -> {
			removeSprite(e.getSprites());
		});
		bus.on(SpriteTriggerRegisterEvent.SPRITE_ALL, (e) -> {
			for (Sprite sprite : getSprites()) {
				sprite.on(e.getTriggerBusEventType(), e.getTriggerHandler());
			}
			// later register this trigger for all newly added sprites
			spriteTriggerRegisterEvents.add(e); 
		});
		bus.on(SpriteTriggerRegisterEvent.SPRITE_SPECIFIC, (e) -> {
			getByID(e.getSpriteID()).ifPresent((sprite) -> {
				sprite.on(e.getTriggerBusEventType(), e.getTriggerHandler());
			});
		});
		bus.on(SpriteTriggerActionEvent.BROADCAST, (e) -> {
			BusEvent busEvent = e.getEvent();
			// this event is going to be sent to each sprite for handling
			// TODO should set source of the event, and also clone the event? 
			for (Sprite sprite : getSprites()) {
				sprite.emit(e.getEvent());
			}
		});
		bus.on(SpriteTriggerActionEvent.SPECIFIC, (e) -> {
			getByID(e.getSpriteID()).ifPresent((sprite) -> {
				sprite.emit(e.getEvent());
			});
		});
	}
	private void addSprite(List<Sprite> sprites) {
		for (Sprite sprite : sprites) {
			if (!(this.sprites.contains(sprite))) {
				System.out.println("sprite added to sprite model");
				this.spritesToAdd.add(sprite);
				for (SpriteTriggerRegisterEvent e : spriteTriggerRegisterEvents) {
					sprite.on(e.getTriggerBusEventType(), e.getTriggerHandler());
				}
				sprite.emit(new SetGameBusEvent(bus));
			}
		}
	}

	private void removeSprite(List<Sprite> sprites) {
		for (Sprite sprite : sprites) {
			spritesToRemove.add(sprite);
		}
	}
	
	// TODO added only for loading the model
	public void setBus(EventBus bus) {
		this.bus = bus;
	}
	

	public Optional<Sprite> getByID(SpriteID spriteID) {
		// TODO: a map for faster query? not really necessary
		for (Sprite sprite : sprites) {
			if (sprite.getID().equals(spriteID)) {
				return Optional.of(sprite);
			}
		}
		return Optional.empty();
	}
	
	public List<Sprite> getSprites() {
		return sprites;
	}
	
	public void update(double dt) {
		sprites.removeAll(spritesToRemove);
		spritesToRemove.clear();
		sprites.addAll(spritesToAdd);
		spritesToAdd.clear();
		for (Sprite sprite : sprites) {
			sprite.update(dt);
		}
	}
	
}
