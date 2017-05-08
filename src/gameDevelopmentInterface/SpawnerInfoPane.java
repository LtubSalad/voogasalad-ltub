package gameDevelopmentInterface;
/**
 * The center of the spawner creation screen that simply
 * shows which monster is currently selected for placing
 * into the spawner
 * Jake
 */
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import newengine.sprite.components.Images;

public class SpawnerInfoPane extends BorderPane {
	private ImageView currentMonsterPicture = new ImageView();

	public SpawnerInfoPane() {
		refresh();
	}

	public void setImage(Images monsterImages) {
		currentMonsterPicture = new ImageView(monsterImages.image().getFXImage());
		refresh();
	}

	private void refresh() {
		this.setCenter(currentMonsterPicture);
	}

}
