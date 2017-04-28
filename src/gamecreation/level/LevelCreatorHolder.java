package gamecreation.level;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LevelCreatorHolder extends ScrollPane{
	private VBox holder;
	private HBox addButtons;
	private int numLevels;
	private ArrayList<LevelCreator> levels;
	public static final int DEFAULT_PREF_HEIGHT = 600;

	public LevelCreatorHolder(double prefHeight){
		super();
		this.setFitToHeight(true);
		
		holder = new VBox();
		holder.setPrefHeight(prefHeight);
		
		this.setContent(holder);
		numLevels = 0;
		levels = new ArrayList<LevelCreator>();
		setUpBox();
	}
	
	public LevelCreatorHolder(){
		this(DEFAULT_PREF_HEIGHT);
	}

	
	private void setUpBox(){
		holder.setAlignment(Pos.CENTER);
		addButtons = new HBox();
		//TODO resource file
		Button addDefaultButton = new Button("+ Default Level");
		Button addAdvancedButton = new Button("+ Custom Level");
		//TODO put into resource file
		addAdvancedButton.setOnAction(e -> addAdvancedLevelCreator());
		addDefaultButton.setOnAction(e -> addDefaultLevelCreator());
		Button tester = new Button("tester");
		tester.setOnAction(e -> testPrint());
		addButtons.getChildren().addAll(addDefaultButton, addAdvancedButton, tester);
		holder.getChildren().add(addButtons);
	}
	
	private  void addDefaultLevelCreator(){
		addLevelCreator(new BasicLevelCreator("Level " + (numLevels+1), this));
	}
	private void addAdvancedLevelCreator(){
		addLevelCreator(new AdvancedLevelCreator("Level "+ (numLevels+1), this));
	}
	
	private void addLevelCreator(LevelCreator level){
		numLevels++;
		if(holder.getChildren().size() > 0){
			holder.getChildren().add(holder.getChildren().size()-1, level);
		}
		else{
			holder.getChildren().add(level);
		}
		levels.add(level);
	}
	
	public int getNumberLevels(){
		return numLevels;
	}
	
	/**
	 * This method has been created with extensibility in mind. For example, currently there are two kinds of buttons
	 * implemented that make two different kinds of level creators. However, you might want to have a new kind of LevelCreator
	 * and then can add a button which will do that for you.
	 */
	public void addButton(Button button){
		addButtons.getChildren().add(button);
	}

	public void remove(LevelCreator level) {
		for(int i = 0; i < levels.size(); i++){
			if(level.equals(levels.get(i))){
				numLevels--;
				levels.remove(i);
				//The ArrayList and the holder will always be parallel
				holder.getChildren().remove(i);
			}
		}
	}
	
	public List<LevelData> getData(){
		List<LevelData> data = new ArrayList<LevelData>();
		levels.stream().forEach(e -> data.add(e.getData()));
		return data;
	}
	
	public void testPrint(){
		List<LevelData> levels = getData();
		for(LevelData level : levels){
			System.out.println("\n" + "New Level Beginning:");
			System.out.println(level.getName());
			System.out.println(level.getSpawnTime());
			System.out.print(level.getDamageMultiplier());
		}
	}
}
