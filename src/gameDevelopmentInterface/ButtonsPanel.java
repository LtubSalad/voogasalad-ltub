package gameDevelopmentInterface;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utilities.XStreamHandler;

public class ButtonsPanel extends VBox {
	private static final String SET_NUMBER_OF_COLUMNS = "Set Number of Columns";
	private static final String SET_NUMBER_OF_ROWS = "Set Number of Rows";
	private static final String SAVE_THIS_SETUP = "Save this setup";
	private static final String CHECK_SAVE_PATH = "Check/Save path";
	private static final String START_A_PATH = "Start a path";
	private static final String LOAD_SCREEN_MODEL_FROM_FILE = "Load screenModel from file";
	private Button drawPathButton;
	private Button finishPathButton;
	private Button saveSetupButton;
	private Button loadButton;
	private Button sendNumRows;
	private Button sendNumCols;
	private XStreamHandler xstreamHandler=new XStreamHandler();
	private PathCreator myPathCreator;
	private ScreenModelCreator mySMC;
	private HBox rowsBox = new HBox();
	private HBox colsBox = new HBox();
	private TextArea myNumRowsInput = new TextArea();
	private TextArea myNumColsInput = new TextArea();
	
	public ButtonsPanel(ScreenModelCreator smc) {
		mySMC = smc;
		myPathCreator = new PathCreator(smc);
		makeButtons();
		makeRowColSetters();
		this.getChildren().addAll(drawPathButton, finishPathButton, saveSetupButton,loadButton, rowsBox, colsBox);		
	}

	private void makeRowColSetters() {
		myNumRowsInput.setMaxHeight(50);
		myNumRowsInput.setMaxWidth(100);
		myNumColsInput.setMaxHeight(50);
		myNumColsInput.setMaxWidth(100);
		rowsBox.getChildren().addAll(myNumRowsInput, sendNumRows);
		colsBox.getChildren().addAll(myNumColsInput, sendNumCols);
	}
	
	private void makeButtons() {
		drawPathButton = new Button(START_A_PATH);
		finishPathButton = new Button(CHECK_SAVE_PATH);
		saveSetupButton = new Button(SAVE_THIS_SETUP);
		sendNumRows = new Button(SET_NUMBER_OF_ROWS);
		sendNumCols = new Button(SET_NUMBER_OF_COLUMNS);
		sendNumRows.setOnAction(e -> mySMC.getScreen().setNumRows(Integer.parseInt(myNumRowsInput.getText())));
		sendNumCols.setOnAction(e -> mySMC.getScreen().setNumCols(Integer.parseInt(myNumColsInput.getText())));
		drawPathButton.setOnAction(e -> {
			myPathCreator.getReplacementPath().clear();
			myPathCreator.makePath();
		});
		finishPathButton.setOnAction(e -> myPathCreator.replacePath());
		saveSetupButton.setOnAction(e -> {
			xstreamHandler.saveToFile(mySMC.getScreenData().getDataToSave());
		});
		loadButton=new Button(LOAD_SCREEN_MODEL_FROM_FILE);
		loadButton.setOnAction((click)->{
			mySMC.getScreenData().setObjectsOnScreen(xstreamHandler.getScreenModelFile());
		});
	}
}