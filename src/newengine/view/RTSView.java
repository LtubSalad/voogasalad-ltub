/**
 * 
 */
package newengine.view;

import bus.EventBus;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import newengine.model.PlayerStatsModel;
import newengine.model.SelectionModel;
import newengine.model.SpriteModel;
import newengine.view.camera.Camera;
import newengine.view.subview.SkillBox;

/**
 * @author Zhiyong
 *
 */
public class RTSView implements IView{
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static final int CANVAS_HEIGHT = 300;
	
	private EventBus bus;
	private Camera camera;
	private Scene scene;
	private Canvas gameWorldCanvas;
	private GraphicsContext gc;
	private HBox bottomPane;
	private GraphicsContext gcSelected;
	private SkillBox skillBox;
	
	public RTSView(EventBus bus, Camera camera) {
		this.bus = bus;
		this.camera = camera;
		VBox root = new VBox();
		
		Paint background = 
		scene = new Scene(root, WIDTH, HEIGHT, background);
		gameWorldCanvas = new Canvas(WIDTH, CANVAS_HEIGHT);
		bottomPane = new HBox();
		root.getChildren().addAll(gameWorldCanvas, bottomPane);
		// selected sprite
		Canvas selectionCanvas = new Canvas(WIDTH / 2, HEIGHT - CANVAS_HEIGHT);
		bottomPane.getChildren().add(selectionCanvas);
		gc = gameWorldCanvas.getGraphicsContext2D();
		gcSelected = selectionCanvas.getGraphicsContext2D();
		// skill box
		skillBox = new SkillBox(bus);
		bottomPane.getChildren().add(skillBox.getBox());

		initHandlers();
	}

	@Override
	public void render(SpriteModel spriteModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(PlayerStatsModel playerStatsModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SelectionModel selectionModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return null;
	}

}
