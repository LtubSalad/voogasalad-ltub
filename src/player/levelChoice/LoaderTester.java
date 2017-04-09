package player.levelChoice;

import javafx.application.Application;
import javafx.stage.Stage;
import player.gameChoice.GameManager;

public class LoaderTester extends Application {
	public static final String RESOURCES_LOCATION = "resources/menu";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Loader loader = new Loader();
//		GameManager gameManager = new GameManager(primaryStage);
		loader.show();

	}
}