package player.levelChoice;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Zhiyong
 *
 */
public class Loader {
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	public static final Integer STARTTIME = 6;
	private Timeline timeline;
	private Label timerLabel = new Label();
	private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME*100);

	public Loader(){
		
	}

	public void show(){
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Game Loading");
		Group root = new Group();
		Scene scene = new Scene(root, WIDTH, HEIGHT);

		// Bind the timerLabel text property to the timeSeconds property
		timerLabel.textProperty().bind(timeSeconds.divide(100).asString());
		timerLabel.setTextFill(Color.RED);
		timerLabel.setStyle("-fx-font-size: 4em;");

		ProgressBar progressBar = new ProgressBar();
		progressBar.progressProperty().bind(
				timeSeconds.divide(STARTTIME*100.0).subtract(1).multiply(-1));

		timeSeconds.set((STARTTIME)*100);
		timeline = timelineController(primaryStage);

		// gap between components is 20
		VBox vb = new VBox(20);     
		// center the components within VBox
		vb.setAlignment(Pos.CENTER);        
		vb.setPrefWidth(scene.getWidth());
		vb.setLayoutY(30);
		vb.getChildren().addAll( timerLabel, progressBar);
		
		root.getChildren().add(vb);

		primaryStage.setScene(scene);
		primaryStage.show();


	}

	private Timeline timelineController(Stage primaryStage){

		Timeline timeline = new Timeline();

		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(STARTTIME), e -> eventHandle(primaryStage)));

		timeline.getKeyFrames().add(

				new KeyFrame(Duration.seconds(STARTTIME),
						new KeyValue(timeSeconds, 0)));
		timeline.playFromStart();
		return timeline;


	}
	
	private void eventHandle(Stage primaryStage){
		 primaryStage.hide();

		 LevelManager levelManager = new LevelManager();
		 levelManager.show();
		 
		
	}

}
