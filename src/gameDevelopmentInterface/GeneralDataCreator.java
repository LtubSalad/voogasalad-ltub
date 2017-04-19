package gameDevelopmentInterface;

import java.util.ResourceBundle;
import data.GeneralModelData;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

/**
 * Provides a text input box allowing the user to set variables that are tracked
 * regardless of which screen we are on, such as lives, score etc. No public
 * methods, because its actions are event-based. Instantiates new
 * GeneralModelData classes when the save button is pressed on the text input
 * box.
 * 
 * @author Jake
 */
public class GeneralDataCreator extends GridPane {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private static final int INSETS = 50;
	private static final int MAX_SCREEN_SIZE = 600;
	private static final String SAVE_ALL_VALUES = "SAVE_ALL_VALUES";
	private static final String NUM_STARTING_BONUSES = "NUM_STARTING_BONUSES";
	private static final String NUM_STARTING_GOLD = "NUM_STARTING_GOLD";
	private static final String NUM_LEVELS = "NUM_LEVELS";
	private static final String NUM_LIVES = "NUM_LIVES";
	private static final String SEND = "SEND";
	private TextArea myNumLivesInput = new TextArea();
	private TextArea myNumLevelsInput = new TextArea();
	private TextArea myStartingGoldInput = new TextArea();
	private TextArea myStartingBonusesInput = new TextArea();
	private Button sendNumLivesInput = new Button(myResources.getString(SEND));
	private Button sendNumLevelsInput = new Button(myResources.getString(SEND));
	private Button sendStartingGoldInput = new Button(myResources.getString(SEND));
	private Button sendStartingBonusesInput = new Button(myResources.getString(SEND));
	private Button saveAll = new Button(myResources.getString(SAVE_ALL_VALUES));
	private GeneralModelData myGeneralModel = new GeneralModelData();
	private ObservableMap<String, String> myData = myGeneralModel.getAllData();

	public GeneralDataCreator() {
		this.setMaxSize(MAX_SCREEN_SIZE, MAX_SCREEN_SIZE);
		this.setPadding(new Insets(INSETS, INSETS, INSETS, INSETS));
		this.setHgap(INSETS / 5);
		setupButtons();
		placeTiles();
	}

	public ObservableMap<String, String> getAllData() {
		return myData;
	}

	private void placeTiles() {
		this.add(new Text(myResources.getString(NUM_LIVES)), 0, 0);
		this.add(new Text(myResources.getString(NUM_LEVELS)), 1, 0);
		this.add(new Text(myResources.getString(NUM_STARTING_GOLD)), 2, 0);
		this.add(new Text(myResources.getString(NUM_STARTING_BONUSES)), 3, 0);
		this.add(myNumLivesInput, 0, 1);
		this.add(myNumLevelsInput, 1, 1);
		this.add(myStartingGoldInput, 2, 1);
		this.add(myStartingBonusesInput, 3, 1);
		this.add(sendNumLivesInput, 0, 2);
		this.add(sendNumLevelsInput, 1, 2);
		this.add(sendStartingGoldInput, 2, 2);
		this.add(sendStartingBonusesInput, 3, 2);
		this.add(saveAll, 4, 1);
	}

	private void setupButtons() {
		sendNumLivesInput.setOnAction(e -> sendNumLives());
		sendNumLevelsInput.setOnAction(e -> sendNumLevels());
		sendStartingGoldInput.setOnAction(e -> sendStartingGold());
		sendStartingBonusesInput.setOnAction(e -> sendStartingBonuses());
		saveAll.setOnAction(e -> {
			sendNumLives();
			sendNumLevels();
			sendStartingGold();
			sendStartingBonuses();
		});
		saveAll.setMinSize(150, 30);
	}

	private void sendNumLives() {
		myGeneralModel.addData(new Pair<String, String>(myResources.getString(NUM_LIVES), myNumLivesInput.getText()));
	}

	private void sendNumLevels() {
		myGeneralModel.addData(new Pair<String, String>(myResources.getString(NUM_LEVELS), myNumLevelsInput.getText()));
	}

	private void sendStartingGold() {
		myGeneralModel.addData(
				new Pair<String, String>(myResources.getString(NUM_STARTING_GOLD), myStartingGoldInput.getText()));
	}

	private void sendStartingBonuses() {
		myGeneralModel.addData(new Pair<String, String>(myResources.getString(NUM_STARTING_BONUSES),
				myStartingBonusesInput.getText()));
	}
}