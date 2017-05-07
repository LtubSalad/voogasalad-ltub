package utilities;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import player.App;

/**
 * @author Zhiyong
 *show the message to the player whether the registration is
 *successful or not
 */
public class MessageShowing implements PopUpMessage {

	private ResourceBundle myResources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);

	public void show(String message){
		Stage stage = new Stage();		
		Text splash = new Text();		
		splash = new Text(10,50,myResources.getString(message));
		splash.setFont(Font.font(25));
		splash.setFill(Color.DARKVIOLET);
		Pane root = new Pane();
		root.getChildren().add(splash);
		Scene scene = new Scene(root,400,200);
		stage.setScene(scene);
		stage.show();
	}
}