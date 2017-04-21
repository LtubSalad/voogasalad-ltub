package gameDevelopmentInterface.spriteCreator;

import data.DeveloperData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	private static final String DEVELOPER_GUI = "DeveloperGUI";

	public void start(Stage primaryStage) {
		primaryStage.setTitle(DEVELOPER_GUI);
		primaryStage.show();
		Scene scene = new Scene(new SpriteCreationScreen(new DeveloperData()));
		primaryStage.setScene(scene);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
