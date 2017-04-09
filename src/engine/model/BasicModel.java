package engine.model;

import java.util.Collection;
import java.util.List;

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
	
	public BasicModel(SpriteModel spriteModel){
		this.spriteModel = spriteModel; 
		handler = (SpriteHandler) spriteModel.getHandler();
	}

	@Override
	public void update(double dt) {
		Collection<Sprite> sprites = handler.getSprites();
		for (Sprite sprite : sprites) {
			sprite.update(dt);
		}
	}

	@Override
	public PlayerSelectionState getPlayerSelectionState() {
		return selectionState;
	}
	@Override
	public void setPlayerSelectionState(PlayerSelectionState selectionState) {
		this.selectionState = selectionState;
	}



	@Override
	public List<Sprite> getSprites() {
		return (List<Sprite>) handler.getSprites(); 
	}

	@Override
	public int getPoints() {
		return points;
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

	@Override
	public Player getPlayer() {
		return player;
	}

}