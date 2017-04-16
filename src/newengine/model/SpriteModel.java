package newengine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bus.EventBus;
import newengine.events.SpriteModelEvent;
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
			}
		}
	}
	private void removeSprite(List<Sprite> sprites) {
		sprites.removeAll(sprites);
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
