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
	
	private SpriteModel sprites; 
	private ISpriteHandler spriteHandler; 
	private TileModel tiles; 
	
	public BasicModel(EventBus bus, Player player) {
		this.bus = bus;
		this.player = player;
		sprites = new SpriteModel();
		spriteHandler = sprites.getHandler();
		tiles = new TileModel(); 
		initHandlers();
	}

	

	private void initHandlers() {
		bus.on(SpriteModelEvent.ADD, (e) -> {
			addSprite(e.getSprite());
		});
		bus.on(SpriteModelEvent.REMOVE, (e) -> {
			removeSprite(e.getSprite());
		});
		bus.on(TileModelEvent.ADD, (e) -> {
			addTile(e.getSprite());
		});
		bus.on(TileModelEvent.REMOVE, (e) -> {
			removeTile(e.getSprite());
		});	
	}

	@Override
	public void addSprite(Sprite sprite) {
		if (sprite == null && RunningMode.DEV_MODE) {
			System.out.println("Model received null sprite: " + sprite);
		}
		if (sprite != null) {
			spriteHandler.addSprite(sprite);
		}
	}
	@Override
	public void removeSprite(Sprite sprite) {
		spriteHandler.removeSprite(sprite);
	}
	
	@Override
	public List<Sprite> getSprites() {
		return spriteHandler.getSprites(); 
	}

	
	@Override
	public void updatePositions(double dt) {
		for (Sprite sprite : spriteHandler.getSprites()) {
			sprite.updatePos(dt);
		}
	}
	@Override
	public Player getPlayer() {
		return player;
	}
	
	public void addTile(Sprite tile){
		tiles.add(tile);
	}
	
	public void removeTile(Sprite tile){
		tiles.remove(tile);
	}
	
	public List<Sprite> getTiles(){
		return tiles.getTiles();
	}
}