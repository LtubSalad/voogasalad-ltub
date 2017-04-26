package gamecreation.level;

import javafx.application.Application;
import javafx.stage.Stage;

public class LevelTester extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		new LevelCreatorHolder();
	}
	
	public static void main(String[] args){
		launch(args);
	}

}
