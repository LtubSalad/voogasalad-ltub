package engine.view;

import bus.EventBus;
import engine.input.MouseClickEvent;
import engine.model.Model;
import engine.sprite.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FXView implements View {

	private EventBus bus;
	private Group root;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static final Paint BACKGROUND = Color.BISQUE;
	
	public FXView(EventBus bus) {
		this.bus = bus;
		root = new Group();
		scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
		canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		initHandlers();
	}
	
	private void initHandlers() {
		scene.setOnMouseClicked(e -> {
        	bus.emit(new MouseClickEvent(e));
        });
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public void render(Model model) {
		gc.clearRect(0, 0, WIDTH, HEIGHT);
		for (Sprite sprite : model.getSprites()) {
			gc.drawImage(new Image(sprite.getImage().getInputStream()), sprite.x(), sprite.y());
		}
	}

}
