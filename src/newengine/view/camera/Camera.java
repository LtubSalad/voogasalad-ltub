package newengine.view.camera;

import bus.EventBus;
import commons.point.GamePoint;
import commons.point.ViewPoint;
import newengine.events.SpriteModelEvent;
import newengine.events.camera.CameraEvent;
import newengine.events.selection.SelectSpriteEvent;
import newengine.sprite.Sprite;
import newengine.sprite.components.Position;
import newengine.view.View;


/**
 * Convert between {@code GamePoint} and {@code ViewPoint}.
 * @author Yilin
 *
 */
public class Camera {

	private EventBus bus;
	private Sprite selectedSprite;
	private double scaleFactor = 1;
	private double translateX = 0;
	private double translateY = 0;
	private boolean locked = false;
	
	public static final double MAX_FACTOR = 100;
	public static final double MIN_FACTOR = 0.01;
	public static final double MOVE_SPEED_PER_FRAME = 100;
	

	public Camera(EventBus bus){
		this.bus = bus;
		initHandlers();
	}
	
	public void update(double dt) {
		if (locked) {
			bus.emit(new CameraEvent(CameraEvent.FOCUS));
		}
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
		bus.on(SelectSpriteEvent.SINGLE, (e) -> {
			this.selectedSprite = e.getSprite();
			center();
		});
		bus.on(SpriteModelEvent.REMOVE, (e) -> {
			if (this.selectedSprite == e.getSprites()) {
				this.selectedSprite = null;
			}
		});
		bus.on(CameraEvent.FOCUS, (e) -> {
			center();
		});
		bus.on(CameraEvent.LOCK, (e) -> {
			locked = !locked;
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
	
	private void center() {
		if (selectedSprite != null){
			if (selectedSprite.getComponent(Position.TYPE).isPresent()) {
				Position position = selectedSprite.getComponent(Position.TYPE).get();
				translateX = (View.INIT_CANVAS_WIDTH / 2 - position.xPos() * scaleFactor) ;
				translateY = (View.INIT_CANVAS_HEIGHT / 2 - position.yPos() * scaleFactor);
			}		
		}
	}
	
	private void reset() {
		scaleFactor = 1;
		translateX = 0;
		translateY = 0;
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
	
	public ViewPoint gameToView(GamePoint gamePoint) {
		return new ViewPoint(gamePoint.x() * scaleFactor + translateX, gamePoint.y() * scaleFactor + translateY);
	}

	public double getScaleFactor() {
		return this.scaleFactor;
	}
}
