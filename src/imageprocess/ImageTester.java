/**
 * 
 */
package imageprocess;

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
 */
public class ImageTester extends Application{
	public static void main(String[] args){
		launch(args);
		}
		

	
	public void start(Stage primaryStage){
		 primaryStage = new Stage();
			primaryStage.setTitle("Test Image");
			Group root = new Group();
			Scene scene = new Scene(root, 800, 800);
			
			Image image = new Image("resources/bahamut_left.png");
			
			Rectangle r = new Rectangle(image.getWidth(),image.getHeight());
			r.setFill(new ImagePattern(image));

           ImageTransformation it = new ImageTransformation();
           //Set<Point> set1 = it.getMask(image);

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
