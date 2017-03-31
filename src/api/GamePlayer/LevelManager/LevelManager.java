package api.GamePlayer.LevelManager;

/**
 * the interface to manage the game logic (which level to go to, or to quit the game)
 * @author Yilin Gao
 *
 */
public interface LevelManager {
	
	/**
	 * the method to ask the user to choose the next level to play, or to quit the game
	 */
	public void chooseLevel();
}
