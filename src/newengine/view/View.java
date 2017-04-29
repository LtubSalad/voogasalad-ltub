package newengine.view;
import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import commons.point.GamePoint;
import commons.point.ViewPoint;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import newengine.events.camera.CameraEvent;
import newengine.events.input.KeyEvent;
import newengine.events.input.MouseEvent;
import newengine.events.stats.ChangeLivesEvent;
import newengine.model.Models;
import newengine.model.PlayerRelationModel;
import newengine.model.PlayerStatsModel;
import newengine.model.SelectionModel;
import newengine.model.SpriteModel;
import newengine.player.Player;
import newengine.skill.Skill;
import newengine.sprite.Sprite;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.Position;
import newengine.sprite.components.SkillSet;
import newengine.utils.image.LtubImage;
import newengine.view.camera.Camera;
import newengine.view.subview.SkillBox;
public class View {
	private double WIDTH = 600;
	private double HEIGHT = 500;
	private double CANVAS_WIDTH = 600;
	private double CANVAS_HEIGHT = 300;
	private double STATS_HEIGHT = 100;
	private double SELECTION_HEIGHT = 200;
	public static final Paint BACKGROUND = Color.BISQUE;
	private EventBus bus;
	private Camera camera;
	private Scene scene;
	private Canvas gameWorldCanvas;
	private GraphicsContext gc;
	private HBox bottomPane;
	private HBox statsPanel;
	private GraphicsContext gcSelected;
	private SkillBox skillBox;
	
	
	// TODO: mouse location should belong to player input state
	private ViewPoint mousePos;
	
	private NodeManager controlManager = new MenuBarManager();
	private HBox controlPanel;
	
	private NodeManager gridPaneManager = new GridPaneManager();
	private GridPane spriteInformation;

	public View(EventBus bus, Camera camera) {
		
		this.bus = bus;
		VBox root = new VBox();
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		WIDTH = screenBounds.getWidth();
		CANVAS_WIDTH = WIDTH;
		HEIGHT = screenBounds.getHeight();
		SELECTION_HEIGHT = HEIGHT / 5;
		CANVAS_HEIGHT = HEIGHT - SELECTION_HEIGHT - STATS_HEIGHT;
		
		scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
		
		
		//statsPanel = new HBox();
		statsPanel = new HBox();
		
		controlPanel = (HBox) controlManager.getNode();
		
		GridPane gridPaneTop = new GridPane();
		gridPaneTop.add(controlPanel, 0, 0);
		gridPaneTop.add(statsPanel, 1, 0);
		
		gameWorldCanvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		bottomPane = new HBox();
		spriteInformation = (GridPane) gridPaneManager.getNode();
		
		GridPane gridPaneBottom = new GridPane();
		gridPaneBottom.add(spriteInformation, 0, 0);
		gridPaneBottom.add(bottomPane, 1, 0);
		
		
		root.getChildren().addAll(gridPaneTop, gameWorldCanvas, gridPaneBottom);
		// selected sprite
		Canvas selectionCanvas = new Canvas(WIDTH / 2, SELECTION_HEIGHT);
		// skill box
		skillBox = new SkillBox(bus);
		bottomPane.getChildren().add(skillBox.getBox());
		this.camera = new Camera(bus);
		
		initHandlers();
	}
	private void initHandlers() {
		gameWorldCanvas.setOnMouseClicked(e -> {
			ViewPoint viewPos = new ViewPoint(e.getX(), e.getY());
			if (e.getButton() == MouseButton.PRIMARY) {
				bus.emit(new MouseEvent(MouseEvent.LEFT, camera.viewToGame(viewPos)));
			} else if (e.getButton() == MouseButton.SECONDARY) {
				bus.emit(new MouseEvent(MouseEvent.RIGHT, camera.viewToGame(viewPos)));
			}
		});
		gameWorldCanvas.setOnMouseMoved(e -> {
			mousePos = new ViewPoint(e.getX(), e.getY());
			
		});
		gameWorldCanvas.setOnScroll((e) -> {
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
	public Scene getScene() {
		return scene;
	}
	
	public void clear(){
		gc.clearRect(0, 0, WIDTH, CANVAS_HEIGHT);
	}
	
	public void render(Models models) {
		render(models.spriteModel());
		render(models.playerStatsModel(), models.playerRelationModel(), models.selectionModel());
		render(models.selectionModel(), models.playerRelationModel());
	}
	
	private void render(SpriteModel model) {
		// render game cast
		clearGameWorldCanvas();
		for (Sprite sprite : model.getSprites()) {
			if (!sprite.getComponent(Position.TYPE).isPresent() ||
					!sprite.getComponent(Images.TYPE).isPresent()) {
				continue;
			}
			GamePoint spritePos = sprite.getComponent(Position.TYPE).get().pos();
			LtubImage image = sprite.getComponent(Images.TYPE).get().image();
			
			GamePoint gamePos = new GamePoint(spritePos.x() - image.getImagePivot().x(), 
					spritePos.y() - image.getImagePivot().y());
			ViewPoint viewPos = camera.gameToView(gamePos);
			gc.drawImage(image.getFXImage(), viewPos.x(), viewPos.y(), 
					image.getFXImage().getWidth() * camera.getScaleFactor(), 
					image.getFXImage().getHeight() * camera.getScaleFactor());
		}
	}
	
	private void render(PlayerStatsModel playerStatsModel, 
			PlayerRelationModel playerRelationModel, SelectionModel selectionModel) {
		this.statsPanel.getChildren().clear();
		statsPanel.setSpacing(10);
		statsPanel.maxHeight(100);
		statsPanel.getChildren().add(new Text("Game Stats: "));
		selectionModel.getSelectedSprite().ifPresent((sprite) -> {
			sprite.getComponent(Owner.TYPE).ifPresent((owner) -> {
				Player player = owner.player();
				Player mainPlayer = playerRelationModel.getMainPlayer();
				if (player == mainPlayer) {
					createText(playerStatsModel, player).stream().forEach(e -> statsPanel.getChildren().add(e));
					
				}
			});			
		});
	}
	
	private List<Text> createText(PlayerStatsModel playerStatsModel, Player player) {
		
//		playerStatsModel.getWealth(player).ifPresent((wealthMap) -> {
//			for (WealthType type: wealthMap.keySet()) {
//				statsLabels.add(new Text(type + ":" + wealthMap.get(type)));
//			}
//		});
		//TODO map to resource file
		
		List<Text> statsLabels = new ArrayList<Text>();
		playerStatsModel.getLives(player).ifPresent((life) -> {
			statsLabels.add(new Text("Lives:" + life));
		});
		playerStatsModel.getScore(player).ifPresent((score) -> {
			statsLabels.add(new Text("Scores:" + score));
		});
		return statsLabels;
		
	}
	public void render(SelectionModel selectionModel, PlayerRelationModel playerRelationModel) {
		// render the selected sprite and its skill box
		if (selectionModel.getSelectedSprite().isPresent()) {
			System.out.println("Selected sprite is present");
			Sprite sprite = selectionModel.getSelectedSprite().get();
			if (sprite.getComponent(Images.TYPE).isPresent()) {
				clearSelectionCanvas();
				gcSelected.drawImage(sprite.getComponent(Images.TYPE).get().image().getFXImage(), 20, 0);
			}
			if (sprite.getComponent(Owner.TYPE).isPresent()) {
				System.out.println("We have an owner");
				Player player = sprite.getComponent(Owner.TYPE).get().player();
				Player mainPlayer = playerRelationModel.getMainPlayer();
				if (player == mainPlayer) {
					System.out.println("Player is main player");
					if (sprite.getComponent(SkillSet.TYPE).isPresent()) {
						System.out.println("we have a skillset");
						skillBox.render(sprite.getComponent(SkillSet.TYPE).get().skills());
					}
					else {
						skillBox.clear();
					}
				}
				else {
					skillBox.clear();
				}
			}
			else {
				skillBox.clear();
			}
		}
		else {
			clearSelectionCanvas();
			skillBox.clear();
		}
		// render the selected skill
		if (selectionModel.getSelectedSkill().isPresent()) {
			Skill skill = selectionModel.getSelectedSkill().get();
			if (skill.getIcon().isPresent()) {
				Image skillImage = skill.getIcon().get().getFXImage();
				scene.setCursor(new ImageCursor(skillImage));
			}
		}
		else {
			scene.setCursor(Cursor.DEFAULT);
		}
	}
	
	public Canvas getCanvas(){
		return gameWorldCanvas;
	}

	private void clearGameWorldCanvas(){
		gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
	}
	
	private void clearSelectionCanvas() {
		gcSelected.clearRect(0, 0, WIDTH / 2, SELECTION_HEIGHT);
	}
}
