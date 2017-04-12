package player.levelChoice;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import player.App;

/**
 * @author Zhiyong
 *
 */
public class Loader {
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	public static final Integer STARTTIME = 11;
	private  ResourceBundle myResources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);
	private Label timerLabel = new Label();
	private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME*100);

	public Loader(){
		
	}

	public void show(){
		Stage primaryStage = new Stage();
		primaryStage.initStyle(StageStyle.UNDECORATED);
		//primaryStage.setTitle("Game Loading");
		Group root = new Group();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		scene.getStylesheets().setAll("/styleSheets/login.css");
		Image backgroundImage = new Image(getClass().getClassLoader().getResourceAsStream(myResources.getString("loaderImagePath")));
		scene.setFill(new ImagePattern(backgroundImage));

		// Bind the timerLabel text property to the timeSeconds property
		timerLabel.textProperty().bind(timeSeconds.divide(100).asString());
		//timerLabel.setTextFill(Color.RED);
		//timerLabel.setStyle("-fx-font-size: 4em;");
		timerLabel.setId("label");

		ProgressBar progressBar = new ProgressBar();
		progressBar.progressProperty().bind(
				timeSeconds.divide(STARTTIME*100.0).subtract(1).multiply(-1));

		timeSeconds.set((STARTTIME)*100);
		timelineController(primaryStage);
		Label loadingLabel =new Label("Game loading ...");
		//loadingLabel.setTextFill(Color.RED);
		//loadingLabel.setStyle("-fx-font-size: 4em;");
		// gap between components is 20
		loadingLabel.setId("label");
		VBox vb = new VBox(20);     
		// center the components within VBox
		vb.setAlignment(Pos.CENTER);        
		vb.setPrefWidth(scene.getWidth());
		vb.setLayoutY(60);
		vb.getChildren().addAll( timerLabel, progressBar, loadingLabel);
		
		root.getChildren().add(vb);
		scene.getStylesheets().setAll("/styleSheets/login.css");
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
		 
		PasswordManager password = new PasswordManager();
		password.show(primaryStage);
		 
		
	}

}
