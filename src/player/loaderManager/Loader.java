package player.loaderManager;

import java.util.ResourceBundle;
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
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;
import player.App;

/**
 * @author Zhiyong
 * This class shows the process to load the game
 * This program will calculate the time to load the game
 *Initially the time is set to START_TIME
 */
public class Loader {
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	public static final Integer START_TIME = 2;
	public static final String CSS_LOCATION = "/styleSheets/login.css";
	
	private Stage primaryStage;
	private  ResourceBundle myResources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);
	private Label timerLabel = new Label();
	private IntegerProperty timeSeconds = new SimpleIntegerProperty(START_TIME*100);

	public Loader(Stage primaryStage){
		this.primaryStage = primaryStage;
	}

	public void show(){
		Group root = new Group();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		scene.getStylesheets().setAll("/styleSheets/login.css");
		Image backgroundImage = new Image(getClass().getClassLoader().getResourceAsStream(myResources.getString("loaderImagePath")));
		scene.setFill(new ImagePattern(backgroundImage));

		// Bind the timerLabel text property to the timeSeconds property
		timerLabel.textProperty().bind(timeSeconds.divide(100).asString());

		timerLabel.setId("label");

		ProgressBar progressBar = new ProgressBar();
		progressBar.progressProperty().bind(
				timeSeconds.divide(START_TIME*100.0).subtract(1).multiply(-1));

		timeSeconds.set((START_TIME)*100);
		timelineController();
		Label loadingLabel =new Label("Game loading ...");
		loadingLabel.setId("label");
		VBox vb = new VBox(20);     
		// center the components within VBox
		vb.setAlignment(Pos.CENTER);        
		vb.setPrefWidth(scene.getWidth());
		vb.setLayoutY(60);
		vb.getChildren().addAll( timerLabel, progressBar, loadingLabel);

		root.getChildren().add(vb);
		scene.getStylesheets().setAll(CSS_LOCATION);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Timeline timelineController(){

		Timeline timeline = new Timeline();

		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(START_TIME), e -> eventHandle()));

		timeline.getKeyFrames().add(

				new KeyFrame(Duration.seconds(START_TIME),
						new KeyValue(timeSeconds, 0)));
		timeline.playFromStart();
		return timeline;
	}

	private void eventHandle(){
		primaryStage.hide();		
	}

}
