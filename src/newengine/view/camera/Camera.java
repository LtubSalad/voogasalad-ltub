package newengine.view.camera;

import bus.EventBus;
import commons.point.GamePoint;
import commons.point.ViewPoint;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import newengine.events.camera.CameraEvent;


/**
 * Convert between {@code GamePoint} and {@code ViewPoint}.
 * @author Yilin, Zhiyong
 *
 */
public class Camera {
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;
	public static final int CANVAS_HEIGHT = 300;
	public static final Paint BACKGROUND = Color.BISQUE;

	private EventBus bus;
	private double scaleFactor = 1;
	private double translateX = 0;
	private double translateY = 0;
	private Scene scene;
	private GamePoint gameP;
	private ViewPoint viewP;
	
	public static final double MAX_FACTOR = 2.5;
	public static final double MIN_FACTOR = 0.5;
	public static final double MOVE_SPEED_PER_FRAME = 100;
	

	public Camera(EventBus bus){
		this.bus = bus;
	}
	public Camera(EventBus bus, GamePoint gamePoint, ViewPoint viewPoint) {
		this.bus = bus;
		this.gameP = gamePoint;
		this.viewP = viewPoint;
		VBox root = new VBox();
		scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
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
		this.translateX += e.getTranslateXValue();
		this.translateY += e.getTranslateYValue();
	}
	
	private void reset() {
		scaleFactor = 1;
		this.translateX = 0;
		this.translateY = 0;
	}
	

	/**
	 * Convert a Point on the view to a Point in the game.
	 * @param viewPoint a {@code ViewPoint} instance
	 * @return GamePoint
	 */
	public GamePoint viewToGame(ViewPoint viewPoint) {
		return new GamePoint((viewPoint.x() - translateX) / scaleFactor, 
				(viewPoint.y() - translateY) / scaleFactor);

	}
	
	/**
	 * Convert a Point in the game to a Point on the view.
	 * @param gamePoint a {@code GamePoint} instance
	 * @return ViewPoint
	 */
	public ViewPoint gameToView(GamePoint gamePoint) {

		return new ViewPoint(gamePoint.x() * scaleFactor + translateX, 
				gamePoint.y() * scaleFactor + translateY);
	}
	
	public double getScaleFactor() {
		return scaleFactor;
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
	
	public Scene getScene(){
		
		return scene;
	}

}
