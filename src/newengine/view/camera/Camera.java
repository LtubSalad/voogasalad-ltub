package newengine.view.camera;

import bus.EventBus;
import commons.point.GamePoint;
import commons.point.ViewPoint;

/**
 * Convert between {@code GamePoint} and {@code ViewPoint}.
 * @author Yilin, Zhiyong
 *
 */
public class Camera {

	private EventBus bus;
	//the default GamePoint for a camera
	private GamePoint gameP;
	//The default ViewPoint for a camera
	private ViewPoint viewP;
	
	public Camera(EventBus bus){
		this.bus = bus;
		gameP = new GamePoint(0,0);
		viewP = new ViewPoint(0,0);
	}
	
	public Camera(EventBus bus, GamePoint gamePoint, ViewPoint viewPoint) {
		this.bus = bus;
		this.gameP = gamePoint;
		this.viewP = viewPoint;
	}
	
	// TODO now it is a 1-1 camera, without any scaling
	/**
	 * Convert a Point on the view to a Point in the game.
	 * @param viewPoint a {@code ViewPoint} instance
	 * @return GamePoint
	 */
	public GamePoint viewToGame(ViewPoint viewPoint) {
		return new GamePoint(viewPoint.x() + gameP.x() - viewP.x(), viewPoint.y()+ gameP.y() - viewP.y());
	}
	
	/**
	 * Convert a Point in the game to a Point on the view.
	 * @param gamePoint a {@code GamePoint} instance
	 * @return ViewPoint
	 */
	public ViewPoint gameToView(GamePoint gamePoint) {
		return new ViewPoint(gamePoint.x() + viewP.x() - gameP.x(), gamePoint.y() + viewP.y() - gameP.y());
	}
	
	public GamePoint getGamePoint(){
		return gameP;
	}
	
	public ViewPoint getViewPoint(){
		return viewP;
	}
	
	/**
	 * @param viewPoint the chosen viewPoint
	 * @param rationToZoom  the ration to zoom in or out according to the size to ratio
	 * if ratio > 1, the result is "zoom in"; otherwise, it is "zoom out"
	 */
	//TODO : Zoom around the selection area by the mouse
	public void zoom(ViewPoint viewPoint, double ratioToZoom){
	}
}
