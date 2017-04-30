package gamedata;

import java.io.File;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import newengine.app.Game;

public class GameTest extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// make a file chooser?
		// create game from file 
		

		String filepath = "data/XMLfiles/trash.xml";
		GameCreator creator = new GameCreator();
		Game game = creator.createGame(filepath);

		primaryStage.setScene(game.getScene());
		game.start();
		
		primaryStage.show();
	}
	
	public static void main (String[] args){
		launch(args);
	}
}
