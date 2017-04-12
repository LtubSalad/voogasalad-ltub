package player.levelChoice;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;
import player.gameChoice.GameManager;

public class LoaderTester extends Application {

	private LevelImageManager lim = new LevelImageManager(230,460);
	 private Node myActor;
	 private Node myActor1;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
//	    Loader loader = new Loader();
		GameManager gameManager = new GameManager(primaryStage);
//		loader.show();
		

	}
}