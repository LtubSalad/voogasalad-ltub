package gamedata;

import javafx.application.Application;
import javafx.stage.Stage;
import newengine.app.Game;

public class GameTest extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// make a file chooser?
		// create game from file 
		String filepath = "data/XMLfiles/trash.xml";
		GameCreator creator = new GameCreator(filepath);
		Game game = creator.getGame();
		
		primaryStage.setScene(game.getScene());
		game.start();
		
		primaryStage.show();
	}
	
	public static void main (String[] args){
		launch(args);
	}
}
