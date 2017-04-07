package LevelChoice;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		//Loader loader = new Loader();

		 primaryStage = new Stage();
			primaryStage.setTitle("Game Loading");
			Group root = new Group();
			Scene scene = new Scene(root, 400, 400);
			ResourceBundle myResources = ResourceBundle.getBundle("resources");
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(myResources.getString("bahamut_left.png")));
			ImageView logo = new ImageView(image);
			root.getChildren().add(logo);

			
			primaryStage.setScene(scene);
			primaryStage.show();

	}
}