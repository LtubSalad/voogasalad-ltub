package engine.camera;

/**
 * Convert between Point in the game and Point on the view
 * @author Yilin
 *
 */
public class Camera {

	public Camera() {
		
	}
	
	// TODO now it is a 1-1 camera, without any scaling
	/**
	 * Convert a Point on the view to a Point in the game
	 * @param viewPoint
	 * @return
	 */
	public GamePoint viewToGame(ViewPoint viewPoint) {
		return new GamePoint(viewPoint.x(), viewPoint.y());
	}
	
	/**
	 * Convert a Point in the game to a Point on the view
	 * @param gamePoint
	 * @return
	 */
	public ViewPoint gameToView(GamePoint gamePoint) {
		return new ViewPoint(gamePoint.x(), gamePoint.y());
	}
}
