package newengine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bus.BusEvent;
import bus.EventBus;
import newengine.events.SpriteModelEvent;
import newengine.events.VarMapEvent;
import newengine.events.trigger.SpriteTriggerActionEvent;
import newengine.events.trigger.SpriteTriggerRegisterEvent;
import newengine.sprite.Sprite;
import newengine.sprite.SpriteID;
import newengine.utils.variable.VarKey;
import newengine.utils.variable.VarValue;

/**
 * A container for sprites.
 * @author keping
 *
 */
public class SpriteModel {

	private EventBus bus;
	private List<Sprite> sprites = new ArrayList<>();
	
	public SpriteModel(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private List<SpriteTriggerRegisterEvent> spriteTriggerRegisterEvents;
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
		for (Sprite sprite: sprites) {
			if (!(this.sprites.contains(sprite))) {
				this.sprites.add(sprite);
				for (SpriteTriggerRegisterEvent e : spriteTriggerRegisterEvents) {
					sprite.on(e.getTriggerBusEventType(), e.getTriggerHandler());
				}
				bus.emit(new VarMapEvent(VarMapEvent.ADD, new VarKey("sprite"), new VarValue(sprite)));
			}
		}
	}
	private void removeSprite(List<Sprite> sprites) {
		for (Sprite sprite: sprites) {
			if (this.sprites.contains(sprite)) {
				this.sprites.remove(sprite);
				bus.emit(new VarMapEvent(VarMapEvent.REMOVE, new VarKey("sprite"), new VarValue(sprite)));
			}
		}
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
		for (Sprite sprite : sprites) {
			sprite.update(dt);
		}
	}
	
}
