package engine.view;


import bus.EventBus;
import commons.Point;
import engine.input.KeyEvent;
import engine.input.MouseClickEvent;
import engine.model.Model;
import engine.playerstate.PlayerSelectionState;
import engine.playerstate.PlayerSelectionState.SelectionType;
import engine.sprite.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FXView implements View {

	private EventBus bus;
	private Scene scene;
	private GraphicsContext gc;
	private HBox bottomPane;
	private GraphicsContext gcSelected;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static final int CANVAS_HEIGHT = 300;
	public static final Paint BACKGROUND = Color.BISQUE;
	
	public FXView(EventBus bus) {
		this.bus = bus;
		VBox root = new VBox();
		scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
		Canvas canvas = new Canvas(WIDTH, CANVAS_HEIGHT);
		bottomPane = new HBox();
		root.getChildren().addAll(canvas, bottomPane);
		Canvas selectionCanvas = new Canvas(WIDTH / 2, HEIGHT - CANVAS_HEIGHT);
		bottomPane.getChildren().add(selectionCanvas);
		gc = canvas.getGraphicsContext2D();
		gcSelected = selectionCanvas.getGraphicsContext2D();
		initHandlers();
	}
	
	private void initHandlers() {
		scene.setOnMouseClicked(e -> {
        	bus.emit(new MouseClickEvent(e));
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
	
	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public void render(Model model) {
		// render game cast 
		gc.clearRect(0, 0, WIDTH, CANVAS_HEIGHT);
		for (Sprite sprite : model.getSprites()) {
			gc.drawImage(new Image(sprite.getImage().getInputStream()), sprite.getDisplayPos().x(), sprite.getDisplayPos().y());
		}
		
		// render selection graphics
		gcSelected.clearRect(0, 0, WIDTH, HEIGHT - CANVAS_HEIGHT);
//		gcSelected.fillOval(0, 0, 30, 40);
		PlayerSelectionState selectionState = model.getPlayerSelectionState();
		if (selectionState.getSelectionType() == SelectionType.SINGLE) {
			Sprite selectedSprite = selectionState.getSelectedSprite();
			gcSelected.drawImage(new Image(selectedSprite.getImage().getInputStream()), 20, 20);
		}
	}

}
