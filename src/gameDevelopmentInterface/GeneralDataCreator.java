package gameDevelopmentInterface;

import java.util.ResourceBundle;

import data.DeveloperData;
import gameauthorgui.inputhelpers.IntegerInputText;
import javafx.collections.ObservableMap;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Pair;

/**
 * Provides a text input box allowing the user to set variables that are tracked
 * regardless of which screen we are on, such as lives, score etc. No public
 * methods, because its actions are event-based. Instantiates new
 * GeneralModelData classes when the save button is pressed on the text input
 * box.
 * 
 * @author Jake, Matt
 */
public class GeneralDataCreator extends BorderPane{
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private static final int OFFSETS = 50;
	private static final String LEVEL_COMPLETION_BONUS = "LEVEL_COMPLETION_BONUS";
	private static final String NUM_STARTING_GOLD = "NUM_STARTING_GOLD";
	private static final String BUILD_IN_GAME = "BUILD_IN_GAME";
	private static final String NUM_LIVES = "NUM_LIVES";
	private DeveloperData myGeneralModel = new DeveloperData();
	private ObservableMap<String, String> myData = myGeneralModel.getAllData();
	private GeneralGameDataBar myBar = new GeneralGameDataBar(myData);
	private VBox content;
	private IntegerInputText numGold;
	private IntegerInputText numLives;
	private IntegerInputText levelCompletionBonus;
	private CheckBox towerBuild;


	public GeneralDataCreator() {
		super();
		content = new VBox(OFFSETS);
		this.setCenter(content);
		setupTitle();
		initTiles();
		placeTiles();
	}

	public ObservableMap<String, String> getAllData() {
		return myData;
	}
	
	public GeneralGameDataBar getBar() {
		return myBar;
	}
	

	private void initTiles() {
		numLives = new IntegerInputText(myResources.getString(NUM_LIVES));
		numLives.getTextProperty().addListener(e -> sendNumLives());
		
		numGold = new IntegerInputText(myResources.getString(NUM_STARTING_GOLD));
		numGold.getTextProperty().addListener(e -> sendStartingGold());
		
		levelCompletionBonus = new IntegerInputText(myResources.getString(LEVEL_COMPLETION_BONUS));
		levelCompletionBonus.getTextProperty().addListener(e -> sendLevelBonuses());
		
		towerBuild = new CheckBox(myResources.getString(BUILD_IN_GAME));
		towerBuild.selectedProperty().addListener(e -> sendTowerBuild());
	}
	
	private void setupTitle(){
		Text title = new Text("General Game Data");
		title.setFont(new Font(40));	
		title.setTextAlignment(TextAlignment.CENTER);
		this.setTop(title);
	}

	private void placeTiles() {
		content.getChildren().addAll(numLives, numGold, levelCompletionBonus, towerBuild);
	}
	
	private void sendNumLives() {
		myGeneralModel.addData(new Pair<String, String>(myResources.getString(NUM_LIVES), numLives.getValue()));
		System.out.println("check" + numLives.getValue());
	}


	private void sendStartingGold() {
		myGeneralModel.addData(
				new Pair<String, String>(myResources.getString(NUM_STARTING_GOLD), numGold.getValue()));
	}

	private void sendLevelBonuses() {
		myGeneralModel.addData(new Pair<String, String>(myResources.getString(LEVEL_COMPLETION_BONUS),
				levelCompletionBonus.getValue()));
	}
	
	private void sendTowerBuild(){
		myGeneralModel.addData(new Pair<String, String>(myResources.getString(BUILD_IN_GAME), 
				Boolean.toString(towerBuild.isSelected())));
	}
}