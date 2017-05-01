package player.levelChoice;

import javafx.application.Application;
import javafx.stage.Stage;

public class LevelManagerTester extends Application{
	
	public static void main(String[] args){
		launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		LevelManager l = new LevelManager(stage);
		l.show();
	}

}
