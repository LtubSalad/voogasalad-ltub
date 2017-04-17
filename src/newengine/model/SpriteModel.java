package newengine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bus.EventBus;
import newengine.events.SpriteModelEvent;
import newengine.events.VarMapEvent;
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
	
	private void initHandlers() {
		bus.on(SpriteModelEvent.ADD, (e) -> {
			addSprite(e.getSprites());
		});
		bus.on(SpriteModelEvent.REMOVE, (e) -> {
			removeSprite(e.getSprites());
		});
	}
	private void addSprite(List<Sprite> sprites) {
		for (Sprite sprite: sprites) {
			if (!(this.sprites.contains(sprite))) {
				this.sprites.add(sprite);
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
		return Optional.empty(); // TODO
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
