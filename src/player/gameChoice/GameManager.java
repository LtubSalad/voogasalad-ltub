package player.gameChoice;

import java.io.File;
import java.util.ResourceBundle;

import commons.FileLoader;
import engine.app.GameFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.App;
import player.levelChoice.Loader;
/**
 * The first stage of the Game Player to choose a game to play.
 * @author Yilin Gao
 *
 */
public class GameManager {
	
	private Stage primaryStage;
	private int numberOfDefaultGames;
	private  ResourceBundle myResources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);
	
	private File gameFile;
	private GameData gameData;
	
	private GameFactory gameFactory;
	
	public GameManager(Stage primaryStage) {
		this.primaryStage = primaryStage;
		numberOfDefaultGames = Integer.parseInt(myResources.getString("numberOfDefaultGames"));
		gameFactory = new GameFactory();
		show();
	}
	
	/**
	 * Get the Stage of the JavaFX app
	 * @return Stage
	 */
	public Stage getStage() {
		return primaryStage;
	}
	
	/**
	 * Get the chosen GameData
	 * @return GameData
	 */
	public GameData getGameData() {
		return gameData;
	}
	
	private void show() {
		primaryStage.setTitle("LTUB Game Chooser");
		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, App.WIDTH, App.HEIGHT);
		primaryStage.setScene(scene);
		
		VBox gameButtonBox = initGameChooser();		
		HBox settingBox = initSystemSettings();
		
		borderPane.setCenter(gameButtonBox);
		borderPane.setBottom(settingBox);
		primaryStage.show();
	}

	private HBox initSystemSettings() {
		HBox settingBox = new HBox();
		settingBox.setAlignment(Pos.TOP_RIGHT);
		settingBox.setSpacing(15);
		
		Button musicButton = new Button();
		ImageView musicImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(myResources.getString("music"))));
		musicImageView.setFitWidth(20);
		musicImageView.setPreserveRatio(true);
		musicButton.setGraphic(musicImageView);
		musicButton.setOnMousePressed(e -> {
			// TODO show a menu of several choices of background music
			System.out.println("Here should be a menu of background music choices.");
		});
		settingBox.getChildren().add(musicButton);
		return settingBox;
	}

	private VBox initGameChooser() {
		VBox gameButtonBox = new VBox();
		gameButtonBox.setAlignment(Pos.CENTER);
		gameButtonBox.setSpacing(15);
		addDefaultChoices(gameButtonBox);
		addCustomChoice(gameButtonBox);
		return gameButtonBox;
	}

	private void addDefaultChoices(VBox gameButtonBox) {
		for (int i = 0; i < numberOfDefaultGames; i++) {
			String fileName = myResources.getString("defaultGamePath" + (i+1));
			Image gameImage = new Image(getClass().getClassLoader().getResourceAsStream(fileName));
			ImageView gameImageView = new ImageView(gameImage);
			gameImageView.setFitWidth(App.WIDTH/3);
			gameImageView.setPreserveRatio(true);
			Button gameButton = new Button();
			// TODO
			gameButton.setTooltip(new Tooltip(myResources.getString("defaultGameName" + (i+1))));
			gameButton.setOnMousePressed(e -> {
				loadGameFile(false, gameButton);
			});
			gameButton.setGraphic(gameImageView);
			gameButtonBox.getChildren().add(gameButton);
		}
	}

	private void addCustomChoice(VBox gameButtonBox) {
		Button customGameButton = new Button("Load another Game");
		customGameButton.setOnMousePressed(e -> {
			loadGameFile(true, customGameButton);
		});
		customGameButton.setWrapText(true);
		gameButtonBox.getChildren().add(customGameButton);
	}
	
	private void loadGameFile(boolean custom, Button button) {
		if (custom) {
			FileLoader fileLoader = new FileLoader(primaryStage);
			gameFile = fileLoader.chooseFile();
			if (gameFile != null) {
				gameFactory.loadGame(gameFile);
			}
		}
		else {
			// TODO load the corresponding game file into gameFile
			System.out.println("Game " + button.getTooltip().getText() + " is chosen.");			
		}
		// TODO pass the game data into game engine
		primaryStage.close();
		Loader loader = new Loader();
		loader.show();
	}
}
