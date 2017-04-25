/**
 * 
 */
package newengine.view;

import java.util.Collection;
import bus.EventBus;
import commons.point.ViewPoint;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import newengine.events.camera.CameraEvent;
import newengine.events.input.KeyEvent;
import newengine.events.input.MouseEvent;
import newengine.model.Models;
import newengine.model.PlayerStatsModel;
import newengine.model.SelectionModel;
import newengine.model.SpriteModel;
import newengine.sprite.Sprite;
import newengine.sprite.components.Images;
import newengine.utils.image.LtubImage;
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
	private GraphicsContext gc;
	private HBox bottomPane;
	private SkillBox skillBox;
	private ViewPoint mousePos;
	private BorderPane pane;

	public RTSView(EventBus bus, Camera camera, Collection<Sprite> sprites) {
		this.bus = bus;
		this.camera = camera;
		pane = new BorderPane();
		//TODO: read all the sprites, then add all the sprites on the root as nodes
		NodeManager menuBarManager = new MenuBarManager();
		pane.setTop(menuBarManager.getNode());

		NodeManager stackPaneManager = new StackPaneManager(sprites);
		pane.setCenter(stackPaneManager.getNode());

		NodeManager gridPaneManager = new GridPaneManager();
		pane.setBottom(gridPaneManager.getNode());




		scene = new Scene(pane, WIDTH, HEIGHT);
		// selected sprite
		Canvas selectionCanvas = new Canvas(WIDTH / 2, HEIGHT - CANVAS_HEIGHT);
		Button selectedSpriteButton = new Button();
		for(Sprite sprite : sprites){
			//TODO: get the skill component of each sprite
			if (sprite!=null&&sprite.getComponent(Images.TYPE).isPresent()){
				LtubImage LTUBImageSprite  = sprite.getComponent(Images.TYPE).get().image();
				Image imageSprite = LTUBImageSprite.getFXImage();
				selectedSpriteButton.setGraphic(new ImageView(imageSprite));
			}
		}

		initHandlers();
	}

	private void initHandlers() {
		// TODO Auto-generated method stub
		
		pane.setOnMouseClicked(e -> {
			ViewPoint viewPos = new ViewPoint(e.getX(), e.getY());
			if (e.getButton() == MouseButton.PRIMARY) {
				bus.emit(new MouseEvent(MouseEvent.LEFT, camera.viewToGame(viewPos)));
			} else if (e.getButton() == MouseButton.SECONDARY) {
				bus.emit(new MouseEvent(MouseEvent.RIGHT, camera.viewToGame(viewPos)));
			}
		});
		pane.setOnMouseMoved(e -> {
			mousePos = new ViewPoint(e.getX(), e.getY());
			
		});
		pane.setOnScroll((e) -> {
			// for my mouse, each scroll is 40 pixels
			// e.getDeltaY() is negative when scorll down (zoom in, increase zoom factor)
			bus.emit(new CameraEvent(CameraEvent.ZOOM, e.getDeltaY() / 400));
		});
		scene.setOnKeyPressed(e -> {
			ViewPoint viewPos = new ViewPoint(0, 0);
			if(e.getCode() == KeyCode.L){
				System.out.println("left key is pressed");
			   bus.emit(new KeyEvent(KeyEvent.PRESS, e.getCode()));
			}
			//bus.emit(new KeyEvent(KeyEvent.PRESS, e.getCode()));
		});
		scene.setOnKeyReleased(e -> {
			bus.emit(new KeyEvent(KeyEvent.RELEASE, e.getCode()));
		});
		scene.setOnKeyTyped(e -> {
			bus.emit(new KeyEvent(KeyEvent.TYPE, e.getCode()));
		});

	}

	@Override
	public void render(SpriteModel model) {

	}


	
	
	

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return scene;
	}
	
	public void render(Models models) {

	}

	@Override
	public void render(PlayerStatsModel playerStatsModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SelectionModel selectionModel) {
		// TODO Auto-generated method stub
		
	}
	
	public void clear(){
		
	}

}
