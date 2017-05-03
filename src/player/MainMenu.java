package player;

import java.io.File;

import commons.FileLoader;
import commons.RunningMode;
import gameauthorgui.tower.TowerAuthor;
import gamedata.GameCreator;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import newengine.app.Game;
import player.helpers.GameLoadException;
import user.UsersModel;

public class MainMenu {

	public static final String CSS_LOCATION = "/styleSheets/login.css";
	private static String backgroundImage = "images/ltub.jpeg";
	
	
	private Stage primaryStage;
	private UsersModel usersModel;
	private Scene scene;
	private Button playGame = new Button("Play Game");
	private Button authorGame = new Button("Author Game");
	private Button socialCenter = new Button("Social Center");

	public MainMenu(Stage primaryStage, UsersModel usersModel){	
		this.primaryStage = primaryStage;
		this.usersModel = usersModel;
		BorderPane root = new BorderPane();
		VBox buttons = new VBox();
		buttons.setId("vbox");
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(
				backgroundImage(),
				playGame, 
				authorGame, 
				socialCenter);

		playGame.setId("main-button");
		authorGame.setId("main-button");
		socialCenter.setId("main-button");
		root.setCenter(buttons);		

		initHandlers();
		scene = new Scene(root, App.WIDTH, App.HEIGHT);
		scene.getStylesheets().setAll(CSS_LOCATION);
		primaryStage.setScene(scene);
	}

	private void initHandlers() {
		playGame.setOnMouseClicked((event) -> {
			FileLoader fileLoader = new FileLoader(primaryStage);
			File fileChosen = fileLoader.chooseFile();
			if (fileChosen != null) {
				startGame(fileChosen);
			}
		});
		authorGame.setOnMouseClicked((event) -> {
			TowerAuthor developerView = new TowerAuthor();
			primaryStage.setScene(developerView.getScene());
			primaryStage.setHeight(600);
			primaryStage.setResizable(true);
			//primaryStage.setFullScreen(true);
		});
		socialCenter.setOnMouseClicked((event) -> {
			primaryStage.setScene(usersModel.getUserSocialPage());
		});
	}
	
	private Node backgroundImage() {
		Pane pane = new StackPane();
		ImageView image = new ImageView(backgroundImage);
		image.setPreserveRatio(true);
		image.setFitWidth(300);
		pane.getChildren().add(image);
		pane.getStyleClass().add("ltub-background");
		return pane;
	}

	public Scene getScene() {
		return scene;
	}
	
	private void alertLoadError(String failedGameFilePath) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Game Load Failure");
		alert.setContentText("Failed to load game: "+failedGameFilePath);
		alert.showAndWait();
	}
	
	private void startGame(File gameFile) {
		GameCreator gameCreator = new GameCreator();
		Game game = null;
		try {
			game = gameCreator.createGame(gameFile);
		} catch (GameLoadException e) {
			if (RunningMode.DEV_MODE) {
				alertLoadError(gameFile.getName());
			}
		}
		if (game != null) {
			primaryStage.setScene(game.getScene());
			game.start();
		}
	}


}
