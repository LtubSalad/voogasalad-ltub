package player.winnerorloser;

/**
 * @author Zhiyong
 * Present the result of a game
 * Show the result by the method show()
 *
 */
public interface ResultPresentation {
	/**
	 * @param result
	 * show the result  to the user after the game 
	 * Win or Lose?
	 */
	public void show(ResultAccessor result);
}
