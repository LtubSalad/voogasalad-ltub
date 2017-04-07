package LevelChoice;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
			primaryStage.setTitle("Test Image");
			Group root = new Group();
			Scene scene = new Scene(root, 400, 400);
			//ResourceBundle myResources = ResourceBundle.getBundle("data/images/characters");
			Image image = new Image("src/resources/bahamut_left.png");
//			ImageView logo = new ImageView(image);
//			root.getChildren().add(logo);

			
			primaryStage.setScene(scene);
			primaryStage.show();

	}
}