package gameDevelopmentDemo;

import gameDevelopmentInterface.MasterDeveloperInterface;
import javafx.application.Application;
import javafx.stage.Stage;

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