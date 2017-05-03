package gamecreation.level;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import data.DeveloperData;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utilities.CustomAlert;

/**
 * The goal of this class is to act as a holder for LevelCreator objects. It is a ScrollPane extension, which is
 * designed so that a GUI screen that utilizes this component can have infinite levels if desired. Currently only
 * supports one type of LevelCreator for box but would be easy to extend to hold multiple types. 
 * @author Matthew Tribby
 */
public class LevelCreatorHolder extends ScrollPane{
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	public static final int DEFAULT_PREF_HEIGHT = 600;
	private VBox holder;
	private HBox addButtons;
	private int numLevels;
	private List<LevelCreator> levels;
	private DeveloperData modelData;
	private Class<? extends LevelCreator> clazz;
	

	/**
	 * Creates a holder with a reference to the modelData, the authoring Environment data. This data is necessary
	 * to have because it contains the LevelData which can be created dynamically throughout
	 * @param modelData
	 * @param prefHeight Pref height of the pain
	 * @param clazz Class of the extension of LevelCreator that you want this LevelCreator to have. 
	 */
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
		Button addDefaultButton = new Button(myResources.getString("addLevel"));
		addDefaultButton.setOnAction(e -> {addDefaultLevelCreator();});
		addButtons.getChildren().addAll(addDefaultButton);
		holder.getChildren().add(addButtons);
	}
	
	private  void addDefaultLevelCreator(){
		try {
			Constructor<? extends LevelCreator> ctor = clazz.getConstructor(String.class, LevelCreatorHolder.class, LevelData.class);
			addLevelCreator((LevelCreator) ctor.newInstance("Level " + (numLevels +1), LevelCreatorHolder.this, new LevelData()));
		} catch (Exception e){
			new CustomAlert(AlertType.ERROR, "Cannot create this specific type of LevelCreator").show();
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
	
	/**
	 * Get number of levels that this holder hold
	 * @return num Levels 
	 */
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

	/**
	 * Remove a LevelCreator from this holder
	 * @param level LevelCreator object 
	 */
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
	
	/**
	 * Returns LevelData associated with LevelCreators
	 * @return
	 */
	public List<LevelData> getData(){
		List<LevelData> data = new ArrayList<LevelData>();
		levels.stream().forEach(e -> data.add(e.getData()));
		return data;
	}

}
