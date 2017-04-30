package main;

import data.SerializableDeveloperData;
import gameauthorgui.tower.TowerAuthor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import newengine.app.App;
import utilities.XStreamHandler;

public class Main extends Application {
	private static final String PLAY_A_GAME = "Play a game";
	private static final String CREATE_A_GAME = "Create a game";
	private static final String HOME_SCREEN = "Home Screen";
	private static final String WELCOME = "Welcome!";
	private static final String PATH_TO_STYLE_SHEETS = "/styleSheets/MainStyle.css";
	private Text welcome = new Text(WELCOME);
	private Button create_game;
	private Button play_game;
	private VBox putInRoot = new VBox();
	private BorderPane root = new BorderPane();
	private Scene scene = new Scene(root);
	private TowerAuthor currentTowerGUI = new TowerAuthor(); 
	private XStreamHandler xstream = new XStreamHandler();

	public void start(Stage primaryStage) {
		primaryStage.setTitle(HOME_SCREEN);
		makeButtons(primaryStage);
		root.setPrefSize(500, 500);
		putInRoot.getChildren().addAll(welcome, create_game, play_game);
		root.setCenter(putInRoot);
		scene.getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	private void makeButtons(Stage ps) {
		create_game = new Button(CREATE_A_GAME);
		create_game.setOnAction(e -> {
			currentTowerGUI = new TowerAuthor();
			Stage createGameStage = new Stage();
			createGameStage.setScene(currentTowerGUI.getScene());
			createGameStage.setFullScreen(true);
			createGameStage.show();
		});
		play_game = new Button(PLAY_A_GAME);
		play_game.setOnAction(e -> {
			// TODO: pop up file chooser + read in the game u wanna make + launch it 
			//SerializableDeveloperData data = (SerializableDeveloperData) xstream.getObjectFromFile();
			
			App runScreen = new App(currentTowerGUI.getData());
			try {
				runScreen.start(ps);
			} catch (Exception e1) {
				//TODO remove this
				e1.printStackTrace();
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
