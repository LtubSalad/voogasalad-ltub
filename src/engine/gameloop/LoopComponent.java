package engine.gameloop;

/**
 * Contains an update method, to be called in each frame when 
 * added to the game loop.
 * 
 * @author keping
 *
 */
@FunctionalInterface
public interface LoopComponent {

	/**
	 * Called once in each frame, the elapsed time of that frame is {@code dt}.
	 * @param dt
	 */
	void update(double dt);
	
}
