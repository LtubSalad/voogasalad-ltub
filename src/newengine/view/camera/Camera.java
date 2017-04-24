package newengine.view.camera;

import bus.EventBus;
import commons.point.GamePoint;
import commons.point.ViewPoint;
import javafx.scene.Node;
import newengine.events.camera.CameraEvent;

/**
 * Convert between {@code GamePoint} and {@code ViewPoint}.
 * @author Yilin
 *
 */
public class Camera {

	private EventBus bus;
	private Node root;
	private double scaleFactor;
	
	public static final double MAX_FACTOR = 2.5;
	public static final double MIN_FACTOR = 0.5;
	
	public Camera(EventBus bus, Node root, double scaleFactor) {
		this.bus = bus;
		this.root = root;
		this.scaleFactor = scaleFactor;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(CameraEvent.ZOOM, (e) -> {
			zoom(e);
		});
		bus.on(CameraEvent.MOVE, (e) -> {
			move(e);
		});
		bus.on(CameraEvent.RESET, (e) -> {
			reset();
		});
	}

	private void zoom(CameraEvent e) {
		scaleFactor -= e.getZoomFactor();
		if (scaleFactor > MAX_FACTOR) {
			scaleFactor = MAX_FACTOR;
		}
		else if (scaleFactor < MIN_FACTOR) {
			scaleFactor = MIN_FACTOR;
		}
	}
	
	private void move(CameraEvent e) {
		root.setTranslateX(root.getTranslateX() + e.getTranslateXValue());
		root.setTranslateY(root.getTranslateY() + e.getTranslateYValue());
	}
	
	private void reset() {
		scaleFactor = 1;
		root.setTranslateX(0);
		root.setTranslateY(0);
	}
	
	/**
	 * Convert a Point on the view to a Point in the game.
	 * @param viewPoint a {@code ViewPoint} instance
	 * @return GamePoint
	 */
	public GamePoint viewToGame(ViewPoint viewPoint) {
		return new GamePoint(viewPoint.x() / scaleFactor, viewPoint.y() / scaleFactor);
	}
	
	/**
	 * Convert a Point in the game to a Point on the view.
	 * @param gamePoint a {@code GamePoint} instance
	 * @return ViewPoint
	 */
	public ViewPoint gameToView(GamePoint gamePoint) {
		return new ViewPoint(gamePoint.x() * scaleFactor, gamePoint.y() * scaleFactor);
	}
	
	public double getScaleFactor() {
		return scaleFactor;
	}
}
