/**
 * 
 */
package player.winnerorloser;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Zhiyong
 * test the result showing method
 *
 */
public class WinTester extends Application{
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ResultAccessor r = new ResultAccessor();
		ResultPresentation res = new LosePresentation();
		res.show(r);		
	}
}