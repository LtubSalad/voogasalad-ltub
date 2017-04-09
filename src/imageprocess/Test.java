/**
 * 
 */
package imageprocess;

import java.awt.image.BufferedImage;
import java.util.Set;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Zhiyong
 *
 */
public class Test extends Application{
	public static void main(String[] args){
//		int[][] img = {{0,0,0,0},{0,1,1,0},{1,1,1,0},{0,1,1,0},{1,1,1,1},{1,0,0,0}};
//		CCLabeling c = new CCLabeling(img,6,4);
//		int[][] a = c.compute();
//		for(int i =0; i < a.length; i++){
//			for(int j =0; j < a[0].length;j++){
//				System.out.println("i: " + i + "j: " + j + "    "+ a[i][j]);
//			}
		launch(args);
		}
		

	
	public void start(Stage primaryStage){
		 primaryStage = new Stage();
			primaryStage.setTitle("Test Image");
			Group root = new Group();
			Scene scene = new Scene(root, 400, 400);
			
			Image image = new Image("resources/bahamut_left.png");
//			System.out.println(image.getWidth() + ":"+ image.getHeight());
//			ImageProcessor imageProcessor = new ImageTransformation();
//			Set<Coordinate<Integer, Integer>> maskSet = imageProcessor.getMask(image);
//			int sum = 0;
//			for(Coordinate<Integer, Integer> p : maskSet){
//				if(p.getFirst() == 40){
//					System.out.println(p);
//					sum += 1;
//				}
//			}
//			System.out.println("sum is : "+ sum) ;
			
			Rectangle r = new Rectangle(image.getWidth(),image.getHeight());
			r.setFill(new ImagePattern(image));
	                WritableImage snapshot = r.snapshot(new SnapshotParameters(), null);

//			ImagePattern pattern = new ImagePattern(image);
//			scene.setFill(pattern);


           BufferedImage buffImg1= SwingFXUtils.fromFXImage(image, null);
           BufferedImage buffImg2= SwingFXUtils.fromFXImage(snapshot, null);
           int sum =0;
           for(int i =0; i < image.getWidth(); i++){
        	   for(int j = 0; j < image.getHeight(); j++){
        		   //after snapshot, the pixel value will change just at most 1
        		   if(Math.abs(buffImg1.getRGB(i,j) - buffImg2.getRGB(i,j)) > 1 ){
        			   System.out.println("not good");
        			   sum++;
        		   }
        	   }
           }
           
           System.out.println(buffImg1.getWidth());
           System.out.println(buffImg1.getHeight());
           
           System.out.println(buffImg2.getWidth());
           System.out.println(buffImg2.getHeight());
           root.getChildren().add(r);
           System.out.println("sum is : " + sum);
           
			primaryStage.setScene(scene);
			primaryStage.show();
	}

}
