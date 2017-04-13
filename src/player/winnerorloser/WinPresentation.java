/**
 * 
 */
package player.winnerorloser;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @author Zhiyong
 *
 */
public class WinPresentation implements ResultPresentation{

	@Override
	public void show(ResultAccessor result) {
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Winner");
		Group root = new Group();
		Button playButton = new Button("restart");
		playButton.setLayoutX(50);
		playButton.setLayoutY(50);
		playButton.setOnAction( e -> restartAction());
		
		Button exitButton = new Button("exit");
		exitButton.setLayoutX(50);
		exitButton.setLayoutY(70);
		
		root.getChildren().addAll(playButton, exitButton);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	private void restartAction() {
		// TODO Auto-generated method stub
		
	}

}
