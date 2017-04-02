package main;

import RealGameDevelopment.MasterDeveloperInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainForTestingGUI extends Application {
	public void start(Stage primaryStage) {
		primaryStage.setTitle("DeveloperGUI");
		primaryStage.show();
		MasterDeveloperInterface developerView=new MasterDeveloperInterface();
		primaryStage.setScene(developerView.getScene());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
