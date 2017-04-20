package newengine.view;

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
import newengine.events.input.KeyEvent;
import newengine.events.input.MouseEvent;
import newengine.model.PlayerStatsModel;
import newengine.model.SelectionModel;
import newengine.model.SpriteModel;
import newengine.skill.Skill;
import newengine.sprite.Sprite;
import newengine.sprite.components.Images;
import newengine.sprite.components.Position;
import newengine.sprite.components.SkillSet;
import newengine.utils.image.LtubImage;
import newengine.view.camera.Camera;
import newengine.view.subview.SkillBox;

public class View {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static final int CANVAS_HEIGHT = 300;
	public static final Paint BACKGROUND = Color.BISQUE;

	private EventBus bus;
	private Camera camera;
	private Scene scene;
	private Canvas gameWorldCanvas;
	private GraphicsContext gc;
	private HBox bottomPane;
	private GraphicsContext gcSelected;
	private SkillBox skillBox;
	
	// TODO: mouse location should belong to player input state
	private ViewPoint mousePos;

	public View(EventBus bus, Camera camera) {
		this.bus = bus;
		this.camera = camera;
		VBox root = new VBox();
		scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
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

	public void clear(){
		gc.clearRect(0, 0, WIDTH, CANVAS_HEIGHT);
	}
	
	public void render(SpriteModel model) {
		// render game cast
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
			gc.drawImage(new Image(image.getInputStream()), viewPos.x(),
					viewPos.y());
		}

	}
	
	public void render(PlayerStatsModel playerStatsModel) {
		// TODO
	}
	
	public void render(SelectionModel selectionModel) {
		// render the selected sprite and its skill box
		if (selectionModel.getSelectedSprite().isPresent()) {
			Sprite sprite = selectionModel.getSelectedSprite().get();
			if (sprite.getComponent(Images.TYPE).isPresent()) {
				gcSelected.clearRect(0, 0, WIDTH, CANVAS_HEIGHT);
				gcSelected.drawImage(sprite.getComponent(Images.TYPE).get().image().getFXImage(), 20, 0);
			}
			if (sprite.getComponent(SkillSet.TYPE).isPresent()) {
				skillBox.render(sprite.getComponent(SkillSet.TYPE).get().skills());
			}
			else {
				skillBox.clear();
			}
		}
		else {
			gcSelected.clearRect(0, 0, WIDTH, CANVAS_HEIGHT);
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

}
