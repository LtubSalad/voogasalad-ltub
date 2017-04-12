package player.loaderManager;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;
import javafx.util.Duration;
import player.gameChoice.GameManager;
import player.levelChoice.LevelImageManager;

public class LoaderTester extends Application {
	private LevelImageManager lim = new LevelImageManager(230,460);
	 private Node myActor;
	 private Node myActor1;
	 private Node myActor2;

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
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreenExitKeyCombination(null);
		primaryStage.show();
		primaryStage.setFullScreen(true);
        Animation myAnimation = makeAnimation(myActor, 100, 200, 20);
        // start animation
        myAnimation.play();
        
        Animation myAnimation1 = makeAnimation(myActor1, 100, 200, 20);
        // start animation
        myAnimation1.play();
        
        Animation myAnimation2 = makeAnimation(myActor2,100,240,60);
        // start animation
        myAnimation2.play();



	}
	
    private Animation makeAnimation (Node agent, int x, int y, int z) {
        // create something to follow
        Path path = new Path();
        path.getElements().addAll(new MoveTo(x, y), new VLineTo(z));
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
        Button btnQuit = new Button("Quit");
        root.getChildren().add(btnQuit);
        myActor2= btnQuit;
        return new Scene(root, 400, 700);
    }
}