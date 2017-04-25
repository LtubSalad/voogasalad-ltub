package gameDevelopmentDemo;

import java.util.ArrayList;
import java.util.List;

import commons.point.GamePoint;
import data.SpriteMakerModel;
import gameDevelopmentInterface.MasterDeveloperInterface;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.events.sprite.MoveEvent;
import newengine.player.Player;
import newengine.sprite.Sprite;
import newengine.utils.Target;
import newengine.utils.image.ImageSet;
import newengine.utils.image.LtubImage;
import utilities.XStreamHandler;

public class MainForTestingGUI extends Application {
	private static final String DEVELOPER_GUI = "DeveloperGUI";

	public void start(Stage primaryStage) {
		primaryStage.setTitle(DEVELOPER_GUI);
		primaryStage.show();
		MasterDeveloperInterface developerView = new MasterDeveloperInterface();
		primaryStage.setScene(developerView.getScene());
	}

	public static void main(String[] args) {
		launch(args);
	}
}