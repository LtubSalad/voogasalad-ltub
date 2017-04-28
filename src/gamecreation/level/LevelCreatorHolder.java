package gamecreation.level;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import data.DeveloperData;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LevelCreatorHolder extends ScrollPane{
	public static final int DEFAULT_PREF_HEIGHT = 600;
	private VBox holder;
	private HBox addButtons;
	private int numLevels;
	private List<LevelCreator> levels;
	private DeveloperData modelData;
	private Class<? extends LevelCreator> clazz;
	

	public LevelCreatorHolder(DeveloperData modelData, double prefHeight, Class<? extends LevelCreator> clazz){
		super();
		this.clazz = clazz;
		this.modelData = modelData;
		
		holder = new VBox();
		holder.setPrefHeight(prefHeight);
		
		this.setContent(holder);
		numLevels = 0;
		levels = new ArrayList<LevelCreator>();
		setUpBox();
	
	}
	
	public LevelCreatorHolder(DeveloperData modelData, Class <? extends LevelCreator> clazz){
		this(modelData, DEFAULT_PREF_HEIGHT, clazz);
	}

	
	private void setUpBox(){
		holder.setAlignment(Pos.CENTER);
		addButtons = new HBox();
		//TODO resource file
		Button addDefaultButton = new Button("+ Level");
		addDefaultButton.setOnAction(e -> addDefaultLevelCreator());
	
		addButtons.getChildren().addAll(addDefaultButton);
		holder.getChildren().add(addButtons);
	}
	
	private  void addDefaultLevelCreator(){
		//addLevelCreator(new BasicLevelCreator("Level " + (numLevels+1), this));
		try {
			Constructor<? extends LevelCreator> ctor = clazz.getConstructor(String.class, LevelCreatorHolder.class, LevelData.class);
			addLevelCreator((LevelCreator) ctor.newInstance("Level " + (numLevels +1), LevelCreatorHolder.this, new LevelData()));
		} catch (Exception e){
			//FIXME
			e.printStackTrace();
			System.out.println("No such class found");
		}
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
		modelData.getLevelData().add(level.getData());
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
				modelData.getLevelData().remove(i);
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
		}
	}
}
