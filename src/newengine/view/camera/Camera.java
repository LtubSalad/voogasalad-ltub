package newengine.view.camera;

import bus.EventBus;
import commons.point.GamePoint;
import commons.point.ViewPoint;

/**
 * Convert between Point in the game and Point on the view
 * @author Yilin
 *
 */
public class Camera {

	private EventBus bus;
	
	public Camera(EventBus bus) {
		this.bus = bus;
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
