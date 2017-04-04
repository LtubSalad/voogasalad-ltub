package engine.view;

import engine.sprite.Sprite;
import javafx.scene.Scene;

public interface View {

	public void addSprite(Sprite sprite);
	
	public void removeSprite(Sprite sprite);
	
	public Scene getScene();
	
}
