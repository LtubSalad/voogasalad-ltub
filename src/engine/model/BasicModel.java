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
	private List<Sprite> monsterSprites = new ArrayList<>();
	private List<Sprite> towerSprites = new ArrayList<>();
	private List<Sprite> bulletSprites = new ArrayList<>();

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
		bus.on(SpriteModelEvent.ADD, (e) -> {
			addMonster(e.getSprite());
		});
		bus.on(SpriteModelEvent.ADD, (e) -> {
			removeMonster(e.getSprite());
		});
		bus.on(SpriteModelEvent.ADD, (e) -> {
			addTower(e.getSprite());
		});
		bus.on(SpriteModelEvent.ADD, (e) -> {
			removeTower(e.getSprite());
		});
		bus.on(SpriteModelEvent.ADD, (e) -> {
			addBullet(e.getSprite());
		});
		bus.on(SpriteModelEvent.ADD, (e) -> {
			removeBullet(e.getSprite());
		});
		
	}

	public void addMonster(Sprite sprite) {
		if (sprite == null && RunningMode.DEV_MODE) {
			System.out.println("Model received null sprite: " + sprite);
		}
		if (sprite !=null && sprite.isMonster()){
			monsterSprites.add(sprite);
		}
	}
	
	public void removeMonster(Sprite sprite) {
		monsterSprites.remove(sprite);
	}
	
	public void addTower(Sprite sprite){
		if (sprite == null && RunningMode.DEV_MODE) {
			System.out.println("Model received null sprite: " + sprite);
		}
		if (sprite != null && sprite.isTower()){
			towerSprites.add(sprite);
		}
	}
	public void removeTower(Sprite sprite) {
		towerSprites.remove(sprite);
	}
	
	public void addBullet(Sprite sprite) {
		if (sprite == null && RunningMode.DEV_MODE) {
			System.out.println("Model received null sprite: " + sprite);
		}
		if (sprite !=null && sprite.isMonster()){
			bulletSprites.add(sprite);
		}
	}
	
	public void removeBullet(Sprite sprite) {
		bulletSprites.remove(sprite);
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
	
	public List<Sprite> getMonsterSprites(){
		return monsterSprites;
	}
	
	public List<Sprite> getTowerSprites(){
		return towerSprites;
	}
	
	public List<Sprite> getBulletSprites(){
		return bulletSprites;
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