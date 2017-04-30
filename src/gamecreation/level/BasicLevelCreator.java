package gamecreation.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import gameauthorgui.inputhelpers.ComboBoxParameterInput;
import gameauthorgui.inputhelpers.StringParameterInput;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import newengine.managers.conditions.GoldMinimumCondition;
import newengine.managers.conditions.ICondition;
import newengine.managers.conditions.NoLivesCondition;
import newengine.managers.conditions.NoMonstersCondition;

public class BasicLevelCreator extends LevelCreator{
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private VBox content;
	private Map<String, Class<? extends ICondition>> winningConditions;
	private Map<String, Class<? extends ICondition>> losingConditions;

	public BasicLevelCreator(int i, LevelCreatorHolder parent, LevelData data) {
		this(Integer.toString(i), parent, data);
	}
	public BasicLevelCreator(String name, LevelCreatorHolder parent, LevelData data) {
		super(name, parent, data);
	}
	
	private void initConditions(){
		winningConditions = new HashMap<String, Class<? extends ICondition>>();
		losingConditions = new HashMap<String, Class<? extends ICondition>>();
		winningConditions.put(myResources.getString("noMonsters"), NoMonstersCondition.class);
		winningConditions.put(myResources.getString("goldMin"), GoldMinimumCondition.class);
		losingConditions.put(myResources.getString("noLives"), NoLivesCondition.class);
	}
	
	@Override
	public void createContent() {
		content = new VBox();
		initConditions();
		//TODO resource file
		StringParameterInput title = new StringParameterInput(myResources.getString("levelTitle"));
		title.getTextProperty().addListener(e -> BasicLevelCreator.this.getData().setName(title.getValue()));
		
		ComboBoxParameterInput winningCondition = new ComboBoxParameterInput(myResources.getString("winningCondition"), new ArrayList<String>(winningConditions.keySet()));
		ComboBoxParameterInput losingCondition = new ComboBoxParameterInput(myResources.getString("losingCondition"), new ArrayList<String> (losingConditions.keySet()));
//		winningCondition.getValueProperty().addListener(e -> {
//			if(winningCondition.getValue().contains("(Input)")){
//				winningCondition.appendTextInput();
//			}
//			else{
//				winningCondition.removeTextInput();
//				try {
//					getData().setWinningCondition(winningConditions.get(winningCondition.getValue()).newInstance());
//				} catch(Exception exc){
//					//FIXME
//					exc.printStackTrace();
//					System.out.println("Not a valid condition");
//				}
//			}
//		});
		
	
		Button remove = new Button(myResources.getString("remove"));
		remove.setOnAction(e -> super.remove(this));
		
		content.getChildren().addAll(title, winningCondition, losingCondition, remove);
		this.setContent(content);
	}
	


	
}
