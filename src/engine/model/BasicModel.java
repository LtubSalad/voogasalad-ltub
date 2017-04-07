package engine.model;

import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import commons.RunningMode;
import engine.playerstate.PlayerSelectionState;
import engine.playerstate.PlayerSkillState;
import engine.sprite.Sprite;

public class BasicModel implements Model {

	private EventBus bus;
	private List<Sprite> sprites = new ArrayList<>();
	private PlayerSelectionState selectionState;
	private PlayerSkillState playerSkillState;

	public BasicModel(EventBus bus) {
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
	
	@Override
	public List<Sprite> getSprites() {
		return sprites;
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
	public void update(double dt) {
		for (Sprite sprite : sprites) {
			sprite.update(dt);
		}
	}

}
