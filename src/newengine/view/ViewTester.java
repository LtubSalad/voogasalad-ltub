/**
 * 
 */
package newengine.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author Zhiyong
 *
 */
public class ViewTester extends Application{

	@Override
	public void start(Stage stage) {
		stage.setTitle("Test");
		MenuBarManager m = new MenuBarManager();
		
		Scene scene = new Scene((HBox)m.getNode(), 800,500);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args){
		launch(args);
	}

}
