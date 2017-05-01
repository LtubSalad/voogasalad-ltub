package gamecreation.level;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import gameauthorgui.inputhelpers.ComboBoxParameterInput;
import gameauthorgui.inputhelpers.StringParameterInput;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import newengine.managers.conditions.Condition;
import newengine.managers.conditions.GoldMinimumCondition;
import newengine.managers.conditions.NoLivesCondition;
import newengine.managers.conditions.NoMonstersCondition;

public class BasicLevelCreator extends LevelCreator{
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private VBox content;
	private Map<String, Class<? extends Condition>> winningConditions;
	private Map<String, Class<? extends Condition>> losingConditions;
	private ComboBoxParameterInput winningCondition;
	private ComboBoxParameterInput losingCondition;
	

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
	
	@Override
	public void createContent() {
		content = new VBox();
		initConditions();
		//TODO resource file
		StringParameterInput title = new StringParameterInput(myResources.getString("levelTitle"));
		title.getTextProperty().addListener(e -> BasicLevelCreator.this.getData().setName(title.getValue()));
		
		winningCondition = new ComboBoxParameterInput(myResources.getString("winningCondition"), new ArrayList<String>(winningConditions.keySet()));
		losingCondition = new ComboBoxParameterInput(myResources.getString("losingCondition"), new ArrayList<String> (losingConditions.keySet()));
		Button saveCondition = new Button("Save Conditions");
		saveCondition.setOnAction(e -> createConditions());
		
		Button remove = new Button(myResources.getString("remove"));
		remove.setOnAction(e -> super.remove(this));
		
		content.getChildren().addAll(title, winningCondition, losingCondition, saveCondition, remove);
		this.setContent(content);
	}
	
	private void createConditions(){
		getData().setWinningCondition(createCondition(winningCondition, winningConditions));
		getData().setLosingCondition(createCondition(losingCondition, losingConditions));
	}
	
	private Condition createCondition(ComboBoxParameterInput conditionBox, Map<String, Class<? extends Condition>> map){
		if(conditionBox.getValue().contains("(Input)")){
			try {
				System.out.println(map.get(conditionBox.getValue()).getConstructor(int.class).newInstance(Integer.parseInt(conditionBox.getTextInput())).getClass().getName());
				return map.get(conditionBox.getValue()).getConstructor(int.class).newInstance(Integer.parseInt(conditionBox.getTextInput()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				System.out.println( map.get(conditionBox.getValue()).newInstance().getClass().getName());
				return map.get(conditionBox.getValue()).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
//	private class ComboListener implements ChangeListener<String> {
//		private ComboBoxParameterInput input;
//		
//		public ComboListener(ComboBoxParameterInput input){
//			this.input = input;
//		}
//
//		@Override
//		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//			if(input.getValue().contains("(Input)")){
//				input.appendTextInput();
//				input.getDoneButton().setOnAction(e -> createCondition());
//			}
//			else{
//				input.removeTextInput();
//				createCondition();
//			}
//		}
//		
//		private void createCondition(){
//			try {
//				if(input.getValue().contains("(Input)")
//				
//				getData().setWinningCondition(winningConditions.get(input.getValue()).newInstance());
//			} catch(Exception exc){
//				//FIXME
//				exc.printStackTrace();
//				System.out.println("Not a valid condition");
//			}
//		}
//			
//	}

}
