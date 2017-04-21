package gameDevelopmentDemo;

import commons.point.GamePoint;
import data.EventHandleData;
import gameDevelopmentInterface.MasterDeveloperInterface;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.events.sprite.MoveEvent;
import newengine.sprite.Sprite;
import newengine.utils.Target;
import utilities.XStreamHandler;

public class MainForTestingGUI extends Application {
	private static final String DEVELOPER_GUI = "DeveloperGUI";

	public void start(Stage primaryStage) {
//		XStreamHandler xStream = new XStreamHandler();
//		//xStream.saveToFile(new EventHandleData());
//		EventHandleData data = (EventHandleData) xStream.getObjectFromFile();
//		Sprite sprite = data.getSprite();
//		sprite.emit(new MoveEvent(MoveEvent.SPECIFIC, sprite, new Target(new GamePoint(0,0))));
		primaryStage.setTitle(DEVELOPER_GUI);
		primaryStage.show();
		MasterDeveloperInterface developerView = new MasterDeveloperInterface();
		primaryStage.setScene(developerView.getScene());
	}

	public static void main(String[] args) {
		launch(args);
	}
}