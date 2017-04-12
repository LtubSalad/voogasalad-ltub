package player.levelChoice;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;
import javafx.util.Duration;
import player.gameChoice.GameManager;

public class LoaderTester extends Application {
	private LevelImageManager lim = new LevelImageManager(230,460);
	 private Node myActor;
	 private Node myActor1;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
	    //Loader loader = new Loader();
		//GameManager gameManager = new GameManager(primaryStage);
		//loader.show();
		primaryStage.setTitle("Game Level Choice");
		

		primaryStage.setScene(makeScene());
		primaryStage.show();
		primaryStage.setFullScreen(true);
        Animation myAnimation = makeAnimation(myActor);
        // start animation
        myAnimation.play();
        
        Animation myAnimation1 = makeAnimation(myActor1);
        // start animation
        myAnimation1.play();


	}
	
    private Animation makeAnimation (Node agent) {
        // create something to follow
        Path path = new Path();
        path.getElements().addAll(new MoveTo(100, 200), new VLineTo(250));
        // create an animation where the shape follows a path
        PathTransition pt = new PathTransition(Duration.millis(4000), path, agent);

        // put them together in order
        return new SequentialTransition(agent, pt);
    }
    
    private Scene makeScene () {
        Group root = new Group();
        // create something to animate
        Rectangle rect = lim.getRectangle();
        root.getChildren().add(rect);
        Rectangle rect1 = new Rectangle(0,0, 30,30);
        rect1.setFill(Color.RED);
        root.getChildren().add(rect1);
        myActor = rect;
        myActor1 = rect1;
        return new Scene(root, 400, 700);
    }
}