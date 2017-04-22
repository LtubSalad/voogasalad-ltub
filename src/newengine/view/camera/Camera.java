package newengine.view.camera;

import bus.EventBus;
import commons.point.GamePoint;
import commons.point.ViewPoint;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import newengine.events.input.KeyEvent;
import newengine.events.input.MouseEvent;

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
	//the default GamePoint for a camera
	private GamePoint gameP;
	//The default ViewPoint for a camera
	private ViewPoint viewP;
	private Scene scene;
	
	public Camera(EventBus bus){
		this.bus = bus;
		gameP = new GamePoint(0,0);
		viewP = new ViewPoint(10,10);
		VBox root = new VBox();
		scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
		initHandlers();
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
		// TODO Auto-generated method stub
		
		scene.setOnKeyPressed(e ->{
			if(e.getCode() == KeyCode.LEFT){		
				
				bus.emit(new KeyEvent(KeyEvent.PRESS, e.getCode()));
				//TODO canvas
				
			}
		});
		
		scene.setOnKeyReleased(e ->{		
				System.out.println("left key is released");
				bus.emit(new KeyEvent(KeyEvent.RELEASE, e.getCode()));
				//TODO canvas
		});
		
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
	
	public Scene getScene(){
		
		return scene;
	}

}
