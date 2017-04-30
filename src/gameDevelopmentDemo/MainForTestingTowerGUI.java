package gameDevelopmentDemo;

import gameDevelopmentInterface.TowerDefenseGUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainForTestingTowerGUI extends Application {
	private static final String PATH_TO_STYLE_SHEETS = "/styleSheets/MainStyle.css";
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tower GUI");
		primaryStage.show();
		TowerDefenseGUI myGUI = new TowerDefenseGUI();
		Scene myScene = new Scene(myGUI, 800, 800);
		myScene.getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
		primaryStage.setScene(myScene);
	}

	public static void main(String[] args) {
		launch(args);
	}

}