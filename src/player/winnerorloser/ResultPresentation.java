package player.winnerorloser;

/**
 * @author Zhiyong
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
