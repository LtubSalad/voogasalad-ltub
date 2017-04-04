package engine.view;

import engine.sprite.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;

public class FXView implements View {

	Group root = new Group();
	Scene scene = new Scene(root);
	
	public FXView() {
		
	}
	
	@Override
	public void addSprite(Sprite sprite) {
		// TODO
		root.getChildren().add(null);
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
