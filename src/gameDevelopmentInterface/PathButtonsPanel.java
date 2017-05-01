package gameDevelopmentInterface;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PathButtonsPanel extends VBox {
	private Button startPath;
	private Button checkPath;
	private Button clearScreen;
	private Button refresh;
	private PathCreator myPathCreator;
	private ScreenMap myScreenMap;

	public PathButtonsPanel(PathCreator pathCreator, ScreenMap screenMap) {
		myPathCreator = pathCreator;
		myScreenMap = screenMap;
		makeButtons();
		this.getChildren().addAll(startPath, checkPath, clearScreen, refresh);
	}
	
	private void makeButtons() {
		startPath = new Button("Start Path");
		checkPath = new Button("Add path to paths list");
		clearScreen = new Button("Clear path(s) off screen");
		refresh = new Button("Refresh page");
		startPath.setOnAction(e -> {
			myPathCreator.makePath();
			myPathCreator.replacePath();
		});
		checkPath.setOnAction(e -> {
			myPathCreator.getDeveloperData().addPath(new Path("TestPath", myPathCreator.getReplacementPath()));
		});
		clearScreen.setOnAction(e -> {
			myPathCreator.clearPath();
		});
		refresh.setOnAction(e -> {
			//refresh with new amount of columns and stuff
			myPathCreator.refreshScreen(myScreenMap.getNumRows(), myScreenMap.getNumCols());
		});
	}

}
