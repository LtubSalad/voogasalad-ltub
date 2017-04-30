package gameDevelopmentInterface;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

/**
 * This class is intended to hold all of the clickable items that a user might
 * need to use on the game authoring environment where they wish to set on the
 * screen different sprites.
 * 
 * @author Jake
 *
 */
public class BackgroundButtonsPanel extends TowerSetButtonsPanel {

	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private static final int MAX_WIDTH = 100;
	private static final int MAX_HEIGHT = 50;
	private static final String SET_NUMBER_OF_COLUMNS = "SET_NUMBER_OF_COLUMNS";
	private static final String SET_NUMBER_OF_ROWS = "SET_NUMBER_OF_ROWS";
	private Button sendNumRows;
	private Button sendNumCols;
	private ScreenModelCreator mySMC;
	private HBox rowsBox;
	private HBox colsBox;
	private TextArea myNumRowsInput = new TextArea();
	private TextArea myNumColsInput = new TextArea();

	public BackgroundButtonsPanel(ScreenModelCreator smc) {
		super(smc);
		//makeButtons();
		mySMC = smc;
		rowsBox = new HBox();
		colsBox = new HBox();
		makeRowColSetters();
		this.getChildren().addAll(rowsBox, colsBox);
	}

	private void makeRowColSetters() {
		myNumRowsInput.setMaxHeight(MAX_HEIGHT);
		myNumRowsInput.setMaxWidth(MAX_WIDTH);
		myNumColsInput.setMaxHeight(MAX_HEIGHT);
		myNumColsInput.setMaxWidth(MAX_WIDTH);
		sendNumRows = new Button(myResources.getString(SET_NUMBER_OF_ROWS));
		sendNumCols = new Button(myResources.getString(SET_NUMBER_OF_COLUMNS));
//		sendNumRows.setOnAction(e -> mySMC.getScreen().setNumRows(Integer.parseInt(myNumRowsInput.getText())));
//		sendNumCols.setOnAction(e -> mySMC.getScreen().setNumCols(Integer.parseInt(myNumColsInput.getText())));
//		rowsBox.getChildren().addAll(myNumRowsInput, sendNumRows);
		colsBox.getChildren().addAll(myNumColsInput, sendNumCols);
	}
}