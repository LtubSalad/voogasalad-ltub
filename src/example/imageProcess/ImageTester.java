/**
 * 
 */
package example.imageProcess;

import imageprocess.ImageToPolygon;
import imageprocess.ImageTransformation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Zhiyong
 *
 *Test the convex_hull or Hull methods by providing an image
 *
 *Usage: please change the name of the image directory: "resources/home.png"
 */

public class ImageTester extends Application{
	public static final String IMAGE_DIRECTORY = "resources/home.png";
	public static void main(String[] args){
		launch(args);
		}
		

	
	public void start(Stage primaryStage){
		 primaryStage = new Stage();
			primaryStage.setTitle("Test Image");
			Group root = new Group();
			Scene scene = new Scene(root, 800, 800);
			
			Image image = new Image(IMAGE_DIRECTORY);
			
			Rectangle r = new Rectangle(image.getWidth(),image.getHeight());
			r.setFill(new ImagePattern(image));

           ImageTransformation it = new ImageTransformation();

             root.getChildren().addAll(r);
             
            ImageToPolygon im = new ImageToPolygon(image);
            Polygon pol = im.getPolygon();
            pol.setLayoutX(100);
            pol.setLayoutY(100);
            pol.setFill(Color.RED);
            root.getChildren().add(pol);
			primaryStage.setScene(scene);
			primaryStage.show();
	}

}
