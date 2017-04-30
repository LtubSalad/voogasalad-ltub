package player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.FileLoader;
import commons.RunningMode;
import gamedata.GameCreator;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import newengine.app.Game;
import player.helpers.GameLoadException;
import utilities.GameMetaData;

/**
 * The first stage of the Game Player to choose a game to play. After the user
 * chooses the xml file representing the game, the class passes the file to
 * {@code engine.app.GameFactory}. Depends on {@code engine.app.GameFactory}.
 * 
 * @author Yilin Gao, Keping Wang
 *
 */
public class GameChooser {
	
	public static final String NEW_GAME= "images/games/new_game.jpg";
	
	private Map<String, GameMetaData> presetGames = new HashMap<>(); // hard-coded currently
	private Stage primaryStage;
	
	public GameChooser(Stage primaryStage) {
		this.primaryStage = primaryStage;
		// gameFactory = new GameFactory();
		initPresetGameChoices();
		Scene scene = createScene();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void initPresetGameChoices() {	
		presetGames.put("game1", new GameMetaData("game1", "images/games/supermario_logo.png", "filePath1"));
		presetGames.put("game2", new GameMetaData("game2", "images/games/warcraft_logo.png", "filePath2"));
	}
	
	private Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root, App.WIDTH, App.HEIGHT);
		
		VBox vBox = new VBox();
		//borderPane.setCenter(vBox);
		root.getChildren().add(vBox);
		vBox.setLayoutX(250);
		vBox.setLayoutY(100);
		vBox.getChildren().addAll(createPresetButtons());
		vBox.getChildren().add(createGameFileChooserButton());
		return scene;
	}
	
	private List<Button> createPresetButtons() {
		List<Button> buttons = new ArrayList<>();
		for (String gameName : presetGames.keySet()) {
			GameMetaData gameMeta = presetGames.get(gameName);
			Button button = new Button();
			ImageView gameImageView = new ImageView(gameMeta.getImage());
			gameImageView.setFitWidth(App.WIDTH / 3);
			gameImageView.setPreserveRatio(true);
			button.setGraphic(gameImageView);
			button.setOnMouseClicked((e) -> {
				startGame(gameMeta.getGameFilePath());
			});
			getRotation(button);
			buttons.add(button);
		}
		return buttons;
	}
	
	private Button createGameFileChooserButton() {
		Button button = new Button();
		Image image = new Image(NEW_GAME);
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(App.WIDTH / 3);
		imageView.setPreserveRatio(true);
		button.setGraphic(imageView);
		//button.setWrapText(true);
		button.setOnMouseClicked((e) -> {
			FileLoader fileLoader = new FileLoader(primaryStage);
			File fileChosen = fileLoader.chooseFile();
			if (fileChosen != null) {
				startGame(fileChosen);
			}
		});
		getRotation(button);
		return button;
	}
	
	private void alertLoadError(String failedGameFilePath) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Game Load Failure");
		alert.setContentText("Failed to load game: "+failedGameFilePath);
		alert.showAndWait();
	}
	
	private void startGame(String gameFilePath) {
		startGame(new File(gameFilePath));
	}
	private void startGame(File gameFile) {
		GameCreator gameCreator = new GameCreator();
		Game game = null;
		try {
			game = gameCreator.createGame(gameFile);
		} catch (GameLoadException e) {
			if (RunningMode.DEV_MODE) {
				System.out.println("Development mode stack trace:");
				e.printStackTrace();
				alertLoadError(gameFile.getName());
			}
		}
		if (game != null) {
			primaryStage.setScene(game.getScene());
			game.start();
		}
	}

	private void getRotation(Button button){

		RotateTransition rotation = new RotateTransition(Duration.seconds(3), button);
		rotation.setCycleCount(Animation.INDEFINITE);
		rotation.setByAngle(360);
		
		button.setOnMouseEntered(e -> rotation.play());
		button.setOnMouseExited(e -> rotation.pause());
	}

}
