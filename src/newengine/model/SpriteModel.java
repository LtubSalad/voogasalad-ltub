package newengine.model;

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
	private List<Sprite> sprites;
	
	public SpriteModel(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(SpriteModelEvent.ADD, (e) -> {
			addSprite(e.getSprite());
		});
		bus.on(SpriteModelEvent.REMOVE, (e) -> {
			removeSprite(e.getSprite());
		});
	}
	private void addSprite(Sprite sprite) {
		if (!sprites.contains(sprite)) {
			sprites.add(sprite);
		}
	}
	private void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
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
