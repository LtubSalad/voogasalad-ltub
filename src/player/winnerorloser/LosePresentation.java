/**
 * 
 */
package player.winnerorloser;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import player.App;
import utilities.CustomAlert;

/**
 * @author Zhiyong
 *
 */
public class LosePresentation implements ResultPresentation{

	private VBox vbButtons;
	private Stage primaryStage = new Stage();
	private VBox vbTexts;


	public LosePresentation(){
		vbButtons = new VBox(20);
		vbButtons.setLayoutY(200);
		vbButtons.setLayoutX(500);
		vbButtons.setSpacing(20);
		vbButtons.setPadding(new Insets(0, 20, 10, 20)); 

		vbTexts = new VBox(20);
		vbTexts.setLayoutY(20);
		vbButtons.setLayoutX(100);
		vbTexts.setSpacing(20);
		vbTexts.setPadding(new Insets(0, 20, 10, 20)); 
	}

	@Override
	public void show(ResultAccessor result) {
		primaryStage.setTitle("Loser");
		Group root = new Group();
		Button playButton = new Button("restart");
		playButton.setMaxWidth(Double.MAX_VALUE);
		playButton.setOnAction( e -> restartAction());

		Button exitButton = new Button("exit");
		exitButton.setMaxWidth(Double.MAX_VALUE);
		exitButton.setOnAction(e -> exitAction());

		vbButtons.getChildren().add(playButton);
		vbButtons.getChildren().add(exitButton);

		vbTexts.getChildren().add(getText("You're a Loser!"));
		vbTexts.getChildren().add(getText("For this game:    " + result.getGameName()));
		vbTexts.getChildren().add(getText("You get the score:   " + result.getPoint()));
		vbTexts.getChildren().add(getText("Your health is:    " + result.getHealth()));
		root.getChildren().add(vbTexts);


		root.getChildren().addAll(vbButtons);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}

	private void exitAction() {
		primaryStage.close();

	}

	private void restartAction() {
		App app = new App();
		try {
			app.start(primaryStage);
		} catch (Exception e) {
			new CustomAlert(AlertType.ERROR, "Can't Restart Action after Loss").show();
		}		
	}

	private Text getText(String message){
		Text splash = new Text();		
		splash = new Text(10,50,message);
		splash.setFont(Font.font(25));
		splash.setFill(Color.DARKVIOLET);
		return splash;
	}
}