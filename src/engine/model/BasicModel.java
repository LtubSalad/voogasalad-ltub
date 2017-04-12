package engine.model;

import java.util.ArrayList;
import java.util.List;
import bus.EventBus;
import commons.RunningMode;
import engine.player.Player;
import engine.sprite.Sprite;

public class BasicModel implements Model {
	private EventBus bus;
	private Player player;
	private List<Sprite> sprites = new ArrayList<>();

	public BasicModel(EventBus bus, Player player) {
		this.bus = bus;
		this.player = player;
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

	@Override
	public void addSprite(Sprite sprite) {
		if (sprite == null && RunningMode.DEV_MODE) {
			System.out.println("Model received null sprite: " + sprite);
		}
		if (sprite != null) {
			sprites.add(sprite);
		}
	}
	@Override
	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}
	
	@Override
	public List<Sprite> getSprites() {
		return sprites;
	}

	
	@Override
	public void updatePositions(double dt) {
		for (Sprite sprite : sprites) {
			sprite.updatePos(dt);
		}
	}
	@Override
	public Player getPlayer() {
		return player;
	}
}