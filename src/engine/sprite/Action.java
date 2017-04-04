package engine.sprite;



/**
 * A functional interface for actions that a sprite could perform.
 * Could be queued in an {@link ActionQueue}.
 * @author keping
 *
 */
@FunctionalInterface
public interface Action {

	/**
	 * Invoked to execute the action.
	 */
	void execute();
	
}
