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
	
	public List<Sprite> getSprites();
	public Player getPlayer();
	
	public void update(double dt);
	
}