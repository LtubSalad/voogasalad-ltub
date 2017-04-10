package engine.model;
import java.util.List;
import engine.player.Player;
import engine.sprite.Sprite;
/**
 * Contains data for the overall game.
 * Local player data is stored in {@link PlayerLocalModel}.
 * @author keping
 *
 */
public interface Model {
	public void addSprite(Sprite sprite);
	
	public void removeSprite(Sprite sprite);
	
	public void addMonster(Sprite sprite);
	
	public void removeMonster(Sprite sprite);
	
	public void addTower(Sprite sprite);
	
	public void removeTower(Sprite sprite);
	
	public void addBullet(Sprite sprite);
	
	public void removeBullet(Sprite sprite);
	
	public List<Sprite> getSprites();
	
	public List<Sprite> getMonsterSprites();
	
	public List<Sprite> getTowerSprites();
	
	public List<Sprite> getBulletSprites();
	
	public Player getPlayer();
	
	public void updatePositions(double dt);
	
}