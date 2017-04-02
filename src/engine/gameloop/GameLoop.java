package engine.gameloop;

/**
 * The main game loop. Dependent on {@link LoopComponent} for
 * the {@code update(dt)} method to execute in each frame.
 * @author keping
 *
 */
public interface GameLoop {

	/**
	 * Add a {@link LoopComponent} to the GameLoop, so that the {@code update(double dt)} 
	 * method of the {@link LoopComponent} would be called once each frame when
	 * the loop is running.
	 * 
	 * null object, or {@link LoopComponent} that is previously added, are ignored.
	 * 
	 * @param loopComponent
	 */
	public void addLoopComponent(LoopComponent loopComponent);
	
	/**
	 * Start the game loop.
	 */
	public void start();
	
	/**
	 * Pause the game loop.
	 */
	public void pause();
		
}
