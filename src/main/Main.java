package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import gameDevelopmentInterface.MasterDeveloperInterface;

public class Main extends Application {
	private static final String PATH_TO_STYLE_SHEETS = "/styleSheets/MainStyle.css";
	private Text welcome = new Text("Welcome!");
	private Button create_game;
	private Button play_game;
	private VBox root = new VBox();
	private Scene scene = new Scene(root);

	public void start(Stage primaryStage) {
		primaryStage.setTitle("Home Screen");
		makeButtons(primaryStage);
		root.setPrefSize(250, 250);
		root.getChildren().addAll(welcome, create_game, play_game);
		scene.getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void makeButtons(Stage ps) {
		create_game = new Button("Create a game");
		create_game.setOnAction(e -> {
			MasterDeveloperInterface myDeveloperGUI = new MasterDeveloperInterface();
			ps.setScene(myDeveloperGUI.getScene());
		});
		play_game = new Button("Play a game");
		play_game.setOnAction(e -> {
			
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
