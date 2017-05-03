package gamecreation.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import gameauthorgui.inputhelpers.ComboBoxParameterInput;
import gameauthorgui.inputhelpers.StringParameterInput;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import newengine.managers.conditions.Condition;
import newengine.managers.conditions.GoldMinimumCondition;
import newengine.managers.conditions.NoLivesCondition;
import newengine.managers.conditions.NoMonstersCondition;
import utilities.CustomAlert;

/**
 * Concrete implementation of LevelCreator class. This class takes in a LevelData and will
 * set specific values. In this case, it edits level title, winning condition, and losing condition
 * for the level
 * @author Matthew Tribby
 *
 */
public class BasicLevelCreator extends LevelCreator{
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private VBox content;
	private Map<String, Class<? extends Condition>> winningConditions;
	private Map<String, Class<? extends Condition>> losingConditions;
	private ComboBoxParameterInput winningCondition;
	private ComboBoxParameterInput losingCondition;
	private Button saveCondition;
	private Button remove;
	

	public BasicLevelCreator(int i, LevelCreatorHolder parent, LevelData data) {
		this(Integer.toString(i), parent, data);
	}
	public BasicLevelCreator(String name, LevelCreatorHolder parent, LevelData data) {
		super(name, parent, data);
	}
	
	private void initConditions(){
		winningConditions = new HashMap<String, Class<? extends Condition>>();
		losingConditions = new HashMap<String, Class<? extends Condition>>();
		winningConditions.put(myResources.getString("noMonsters"), NoMonstersCondition.class);
		winningConditions.put(myResources.getString("goldMin"), GoldMinimumCondition.class);
		losingConditions.put(myResources.getString("noLives"), NoLivesCondition.class);
	}
	
	/**
	 * Creates the actual content of creator
	 */
	@Override
	public void createContent() {
		content = new VBox();
		initConditions();
		StringParameterInput title = new StringParameterInput(myResources.getString("levelTitle"));
		title.getTextProperty().addListener(e -> BasicLevelCreator.this.getData().setName(title.getValue()));
		
		winningCondition = new ComboBoxParameterInput(myResources.getString("winningCondition"), new ArrayList<String>(winningConditions.keySet()));
		winningCondition.getComboBox().setValue(winningCondition.getComboBox().getItems().get(1));
		losingCondition = new ComboBoxParameterInput(myResources.getString("losingCondition"), new ArrayList<String> (losingConditions.keySet()));
		winningCondition.getValueProperty().addListener(e -> {
			if(winningCondition.getValue().contains("(Input)")){
				winningCondition.appendTextInput();
			}
			else{
				winningCondition.removeTextInput();
			}
		});
	
		createButtons();
		
		content.getChildren().addAll(title, winningCondition, losingCondition, saveCondition, remove);
		this.setContent(content);
	}
	
	private void createButtons(){
		saveCondition = new Button("Save Conditions");
		saveCondition.setOnAction(e -> createConditions());
		
		remove = new Button(myResources.getString("remove"));
		remove.setOnAction(e -> super.remove());
		
	}
	
	private void createConditions(){
		Condition winning = createCondition(winningCondition, winningConditions);
		Condition losing = createCondition(losingCondition, losingConditions);
		if(winning != null && losing != null){
			getData().setWinningCondition(winning);
			getData().setLosingCondition(losing);
			new CustomAlert(AlertType.CONFIRMATION, "Successful Save!").show();
		}
	}
	
	private Condition createCondition(ComboBoxParameterInput conditionBox, Map<String, Class<? extends Condition>> map){
		Alert errorAlert=new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("Error in saving condition");
		if(conditionBox.getValue().contains("(Input)")){
			try {
				return map.get(conditionBox.getValue()).getConstructor(int.class).newInstance(Integer.parseInt(conditionBox.getTextInput()));
			} catch (Exception e) {
				errorAlert.showAndWait();
			}
		}
		else{
			try {
				return map.get(conditionBox.getValue()).newInstance();
			} catch (Exception e) {
				errorAlert.showAndWait();
			}
		}
		return null;
	}

}
