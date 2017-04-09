package gameDevelopmentInterface;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ButtonsPanel extends VBox {
	private Button drawPathButton = new Button("Start a path");
	private Button finishPathButton = new Button("Check/Save path");
	private PathCreator myPathCreator;
	
	public ButtonsPanel(ScreenModelCreator smc) {
		myPathCreator = new PathCreator(smc);
		makeButtons();
		this.getChildren().addAll(drawPathButton, finishPathButton);		
	}

	private void makeButtons() {
		drawPathButton.setOnAction(e -> {
			myPathCreator.getReplacementPath().clear();
			myPathCreator.makePath();
		});
		finishPathButton.setOnAction(e -> myPathCreator.replacePath());
	}
}