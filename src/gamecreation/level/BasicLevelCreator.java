package gamecreation.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gameauthorgui.inputhelpers.ComboBoxParameterInput;
import gameauthorgui.inputhelpers.StringParameterInput;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import newengine.managers.conditions.GoldMinimumCondition;
import newengine.managers.conditions.ICondition;
import newengine.managers.conditions.NoLivesCondition;
import newengine.managers.conditions.NoMonstersCondition;

public class BasicLevelCreator extends LevelCreator{
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
		winningConditions.put("Gold Minimum", GoldMinimumCondition.class);
		winningConditions.put("No Monsters Left", NoMonstersCondition.class);
		losingConditions.put("No Lives Left", NoLivesCondition.class);
	}
	
	@Override
	public void createContent() {
		content = new VBox();
		initConditions();
		//TODO resource file
		StringParameterInput title = new StringParameterInput("Level Title");
		title.getTextProperty().addListener(e -> BasicLevelCreator.this.getData().setName(title.getValue()));
		
		ComboBoxParameterInput winningCondition = new ComboBoxParameterInput("Winning Condition", new ArrayList<String>(winningConditions.keySet()));
		ComboBoxParameterInput losingCondition = new ComboBoxParameterInput("Losing Condition", new ArrayList<String> (losingConditions.keySet()));
		
		Button remove = new Button("Remove");
		remove.setOnAction(e -> super.remove(this));
		
		content.getChildren().addAll(title, winningCondition, losingCondition, remove);
		this.setContent(content);
	}
	


	
}
