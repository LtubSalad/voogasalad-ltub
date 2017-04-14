package player;

import javafx.application.Application;
import javafx.stage.Stage;
import player.gameChoice.GameManager;

/**
 * The entering point for the game player is to ask the player
 * to chose the game he wants to play.
 * Depends on {@code player.gameChoice.GameManager}.
 * @author Yilin Gao
 *
 */
public class App extends Application {

	public static final String RESOURCES_LOCATION = "resources/menu";
	public final static int WIDTH = 800;
	public final static int HEIGHT = 600;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		new GameManager(primaryStage);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
