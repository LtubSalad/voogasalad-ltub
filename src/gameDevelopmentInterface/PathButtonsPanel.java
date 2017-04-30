package gameDevelopmentInterface;

import java.util.Optional;

import data.DeveloperData;
import gameauthorgui.inputhelpers.IntegerInputText;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PathButtonsPanel extends VBox {
	private Button startPath;
	private Button checkPath;
	private Button clearScreen;
	private Button refresh;
	private PathCreator myPathCreator;
	private ScreenMap myScreenMap;
	private Button sendNumRows;
	private Button sendNumCols;
	private HBox rowsBox = new HBox();
	private HBox colsBox = new HBox();
	private IntegerInputText rowsInput = new IntegerInputText("Number of Rows");
	private IntegerInputText colsInput = new IntegerInputText("Number of Cols");
	private DeveloperData myModel;

	public PathButtonsPanel(DeveloperData model, PathCreator pathCreator, ScreenMap screenMap) {
		myModel = model;
		myPathCreator = pathCreator;
		myScreenMap = screenMap;
		makeButtons();
		rowsBox.getChildren().addAll(rowsInput, sendNumRows);
		colsBox.getChildren().addAll(colsInput, sendNumCols);
		this.getChildren().addAll(startPath, checkPath, clearScreen, refresh, rowsBox, colsBox);
	}
	
	private void makeButtons() {
		startPath = new Button("Start Path");
		checkPath = new Button("Add path to paths list");
		clearScreen = new Button("Clear path(s) off screen");
		refresh = new Button("Refresh page");
		sendNumCols = new Button("Reset number of cols");
		sendNumRows = new Button("Reset number of rows");
		startPath.setOnAction(e -> {
			myPathCreator.makePath();
			myPathCreator.replacePath();
		});
		checkPath.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog("Name of path");
			dialog.setTitle("Name your path");
			dialog.setContentText("Name your path:");

			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				myPathCreator.getDeveloperData().addPath(new Path(result.get(), myPathCreator.getReplacementPath()));
			}
			
			//myPathCreator.getDeveloperData().addPath(new Path("TestPath", myPathCreator.getReplacementPath()));
			
			myPathCreator.getReplacementPath().forEach(gamePoint -> System.out.println(gamePoint.x() + " " + gamePoint.y()));
		});
		clearScreen.setOnAction(e -> {
			myPathCreator.clearPath();
		});
		refresh.setOnAction(e -> {
			//refresh with new amount of columns and stuff
			//myPathCreator.refreshScreen(myScreenMap.getNumRows(), myScreenMap.getNumCols());
		});
		sendNumCols.setOnAction(click -> {
			myModel.setNumCols(Integer.parseInt(colsInput.getValue()));
		});
		sendNumRows.setOnAction(click -> {
			myModel.setNumRows(Integer.parseInt(rowsInput.getValue()));
		});
	}

}
