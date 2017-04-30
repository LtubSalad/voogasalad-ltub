package newengine.view;
import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import commons.point.GamePoint;
import commons.point.ViewPoint;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import newengine.events.camera.CameraEvent;
import newengine.events.input.KeyEvent;
import newengine.events.input.MouseEvent;
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
	public static final Paint BACKGROUND = Color.BISQUE; // Paint is the super class
	public static final double INIT_WIDTH = 600;
	public static final double INIT_HEIGHT = 500;
	public static final double INIT_CANVAS_WIDTH = 600;
	public static final double INIT_CANVAS_HEIGHT = 300;
	public static final double INIT_STAT_HEIGHT = 100;
	public static final double INIT_SELECTION_HEIGHT = 200;
	
	private double width = INIT_WIDTH;
	private double height = INIT_HEIGHT;
	private double canvasWidth = INIT_CANVAS_WIDTH;
	private double canvasHeight = INIT_CANVAS_HEIGHT;
	private double statHeight = INIT_STAT_HEIGHT;
	private double selectionWidth = width / 2;
	private double selectionHeight = INIT_SELECTION_HEIGHT;
	
	private EventBus bus;
	private Camera camera;
	private Scene scene;
	
	private HBox statsPanel = new HBox();
	private Canvas gameWorldCanvas = new Canvas(canvasWidth, canvasHeight);
	private GraphicsContext gc = gameWorldCanvas.getGraphicsContext2D();
	private HBox bottomPane = new HBox();
	private Canvas selectionCanvas = new Canvas(selectionWidth, selectionHeight);
	private GraphicsContext gcSelected = selectionCanvas.getGraphicsContext2D();
	private SkillBox skillBox;


	public View(EventBus bus, Camera camera) {		
		this.bus = bus;
		this.camera = camera;
		initNodes();
		initHandlers();
	}
	
	private void initNodes() {
		VBox root = new VBox();
		scene = new Scene(root, width, height, BACKGROUND);
		root.getChildren().addAll(
				statsPanel, gameWorldCanvas, bottomPane);
		skillBox = new SkillBox(this.bus);
		bottomPane.getChildren().addAll(
				selectionCanvas, (new TowersButton(bus)).getNode(), skillBox.getBox());
		
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
		gameWorldCanvas.setOnScroll((e) -> {
			// for my mouse, each scroll is 40 pixels
			// e.getDeltaY() is negative when scroll down (zoom in, increase zoom factor)
			bus.emit(new CameraEvent(CameraEvent.ZOOM, e.getDeltaY() / 400));
		});
		scene.setOnKeyPressed(e -> {
			bus.emit(new KeyEvent(KeyEvent.PRESS, e.getCode()));
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
	
	public void render(Models models) {
		renderSprites(models.spriteModel());
		renderStats(models.playerStatsModel(), models.playerRelationModel(), models.selectionModel());
		renderBottomPane(models.selectionModel(), models.playerRelationModel());
	}
	
	private List<Text> createText(PlayerStatsModel playerStatsModel, Player player) {
		// TODO map to resource file.
		List<Text> statsLabels = new ArrayList<Text>();
		playerStatsModel.getLives(player).ifPresent((life) -> {
			statsLabels.add(new Text("Lives:" + life));
		});
		playerStatsModel.getScore(player).ifPresent((score) -> {
			statsLabels.add(new Text("Scores:" + score));
		});
		return statsLabels;
	}
	private void renderStats(PlayerStatsModel playerStatsModel, 
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
	
	private void renderSprites(SpriteModel model) {
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		for (Sprite sprite : model.getSprites()) {
			if (!sprite.getComponent(Position.TYPE).isPresent() ||
					!sprite.getComponent(Images.TYPE).isPresent()) {
				continue;
			}
			GamePoint spritePos = sprite.getComponent(Position.TYPE).get().pos();
			sprite.getComponent(Images.TYPE).ifPresent((imagesComponent) -> {
				LtubImage image = imagesComponent.image();
				GamePoint gamePos = new GamePoint(spritePos.x() - image.getImagePivot().x(), 
						spritePos.y() - image.getImagePivot().y());
				ViewPoint viewPos = camera.gameToView(gamePos);
				gc.drawImage(image.getFXImage(), viewPos.x(), viewPos.y(), 
						image.getFXImage().getWidth() * camera.getScaleFactor(), 
						image.getFXImage().getHeight() * camera.getScaleFactor());
			});
		}
	}

	private void renderBottomPane(SelectionModel selectionModel, PlayerRelationModel playerRelationModel) {
		// render the selected skill
		if (selectionModel.getSelectedSkill().isPresent()) {
			Skill skill = selectionModel.getSelectedSkill().get();
			if (skill.getIcon().isPresent()) {
				Image skillImage = skill.getIcon().get().getFXImage();
				scene.setCursor(new ImageCursor(skillImage));
			}
		} else {
			scene.setCursor(Cursor.DEFAULT);
		}
		
		// render skill box of the selected sprite
		if (selectionModel.getSelectedSprite().isPresent()) {
			Sprite sprite = selectionModel.getSelectedSprite().get();
			if (sprite.getComponent(Images.TYPE).isPresent()) {
				clearSelectionCanvas();
				gcSelected.drawImage(sprite.getComponent(Images.TYPE).get().image().getFXImage(), 20, 0);
			}
			if (sprite.getComponent(Owner.TYPE).isPresent()) {
				Player player = sprite.getComponent(Owner.TYPE).get().player();
				Player mainPlayer = playerRelationModel.getMainPlayer();
				if (player == mainPlayer && sprite.getComponent(SkillSet.TYPE).isPresent()) {
					skillBox.render(sprite.getComponent(SkillSet.TYPE).get().skills());
					return;
				}
				skillBox.clear();
			}
		}
		else {
			clearSelectionCanvas();
			skillBox.clear();
		}
	}

	private void clearSelectionCanvas() {
		gcSelected.clearRect(0, 0, width / 2, selectionHeight);
	}
}
