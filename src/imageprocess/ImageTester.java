/**
 * 
 */
package imageprocess;

import java.awt.image.BufferedImage;
import java.util.HashSet;
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
			Scene scene = new Scene(root, 800, 800);
			
			Image image = new Image("resources/home.png");
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
//			Rectangle r2 = new Rectangle(0, 0,image.getWidth(),image.getHeight());


//           BufferedImage buffImg1= SwingFXUtils.fromFXImage(image, null);
           ImageTransformation it = new ImageTransformation();
           Set<Point> set1 = it.getMask(image);
//           System.out.println("the size of set1 is :" + set1.size());
 //          NodeProcessor np = new NodeProcessor();
//           System.out.println("the angle is : " + np.getAngle(r));
           //r.setRotate(360);
//           BufferedImage buffImg2= np.getImage(r);
//           WritableImage i = SwingFXUtils.toFXImage(buffImg2, null);
           //r.setFill(Color.WHITE);
//           r2.setFill(new ImagePattern(i));
//           System.out.println("the set1 is :");
//           for( Coordinate<Integer,Integer> c : set1){
//        	   System.out.println(c);
//           }
//           Set<Point> set2 = it.getMask(i);
//           System.out.println("the set2 is :");
//           System.out.println("the size of set2 is :" + set2.size());
//           for( Coordinate<Integer,Integer> c : set2){
//        	   System.out.println(c);
//           }
//           System.out.println("the size of set2 is :" + set2.size());
           
           
//           Set<Point> intersection = new HashSet<>();
//           for(Point c : set1){
//        	   if(set2.contains(set1)){
//        		   intersection.add(c);
//        	   }
//           }
//           System.out.println("The size of intersection is :" + intersection.size());
//           int[][] pix = new int[(int) image.getWidth()][(int) image.getHeight()];
//           
//           int sum =0;
//           for(int i =0; i < image.getWidth(); i++){
//        	   for(int j = 0; j < image.getHeight(); j++){
//        		   //after snapshot, the pixel value will change just at most 1
//        		   if(Math.abs(buffImg1.getRGB(i,j) - buffImg2.getRGB(i,j)) > 5 ){
//        			   System.out.println("not good");
//        			   sum++;
//        		   }
//        	   }
//           }
//           

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
