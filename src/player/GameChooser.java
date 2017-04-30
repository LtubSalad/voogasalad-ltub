package player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.FileLoader;
import commons.RunningMode;
import gamedata.GameCreator;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, App.WIDTH, App.HEIGHT);
		
		VBox vBox = new VBox();
		borderPane.setCenter(vBox);

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
			buttons.add(button);
		}
		return buttons;
	}
	
	private Button createGameFileChooserButton() {
		Button button = new Button("Load another Game");
		button.setWrapText(true);
		button.setOnMouseClicked((e) -> {
			FileLoader fileLoader = new FileLoader(primaryStage);
			File fileChosen = fileLoader.chooseFile();
			if (fileChosen != null) {
				startGame(fileChosen);
			}
		});
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


}
