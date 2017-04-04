package engine.view;

import bus.EventBus;
import engine.input.MouseClickEvent;
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
		scene.setOnMouseClicked(e -> {
        	bus.emit(new MouseClickEvent(e));
        });
		canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
	}
	
	@Override
	public void addSprite(Sprite sprite) {
		// TODO
		Image realImage = new Image(sprite.getImage().getInputStream());
		gc.drawImage(realImage, 0, 0);
	}

	@Override
	public void removeSprite(Sprite sprite) {
		// TODO Auto-generated method stub		
	}

	@Override
	public Scene getScene() {
		return scene;
	}

}
