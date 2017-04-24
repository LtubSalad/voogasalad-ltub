/**
 * 
 */
package newengine.view;

import bus.EventBus;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import newengine.model.PlayerStatsModel;
import newengine.model.SelectionModel;
import newengine.model.SpriteModel;
import newengine.sprite.Sprite;
import newengine.sprite.components.Images;
import newengine.sprite.components.Speed;
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
	private Canvas gameWorldCanvas;
	private GraphicsContext gc;
	private HBox bottomPane;
	private GraphicsContext gcSelected;
	private SkillBox skillBox;
	
	public RTSView(EventBus bus, Camera camera) {
		this.bus = bus;
		this.camera = camera;
		BorderPane pane = new BorderPane();
		//TODO: read all the sprites, then add all the sprites on the root as nodes
		NodeManager menuBarManager = new MenuBarManager();
		pane.setTop(menuBarManager.getNode());
		
		NodeManager stackPaneManager = new StackPaneManager();
		
		pane.setCenter(stackPaneManager.getNode());
		
		NodeManager gridPaneManager = new GridPaneManager();
		
		pane.setBottom(gridPaneManager.getNode());
		
		
		
		
		scene = new Scene(pane, WIDTH, HEIGHT);
		pane.getChildren().addAll(gameWorldCanvas, bottomPane);
		// selected sprite
		Canvas selectionCanvas = new Canvas(WIDTH / 2, HEIGHT - CANVAS_HEIGHT);
		Button selectedSpriteButton = new Button();
		Sprite sprite = new Sprite();
		//TODO: get the skill component of each sprite
		if (sprite!=null&&sprite.getComponent(Images.TYPE).isPresent()){
			LtubImage LTUBImageSprite  = sprite.getComponent(Images.TYPE).get().image();
			Image imageSprite = LTUBImageSprite.getFXImage();
			selectedSpriteButton.setGraphic(new ImageView(imageSprite));
		}
		
		initHandlers();
	}

	private void initHandlers() {
		// TODO Auto-generated method stub
		
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
