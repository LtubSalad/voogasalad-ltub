package engine.view;

import engine.sprite.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FXView implements View {

	private Group root;
	private Scene scene;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	public static final Paint BACKGROUND = Color.BISQUE;
	
	public FXView() {
		root = new Group();
		scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
	}
	
	@Override
	public void addSprite(Sprite sprite) {
		// TODO
		engine.sprite.Image spriteImage = sprite.getImage();
		String spriteImageFileName = spriteImage.getFileName();
		javafx.scene.image.Image realImage = new javafx.scene.image.Image(getClass().getClassLoader().getResourceAsStream(spriteImageFileName));
		ImageView realImageView = new ImageView(realImage);
		root.getChildren().add(realImageView);
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
