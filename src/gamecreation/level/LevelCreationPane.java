package gamecreation.level;

import java.util.ResourceBundle;

import data.DeveloperData;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class is a GUI component that is meant to hold a LevelCreatorHolder object. It is used as a key pane 
 * in building a game (not type of game specific). Currently holds a BasicLevelCreator option but ideally with
 * future extension and minor code adjustments (currently past deadline so can't change), will accommodate different
 * kinds of level creators. 
 * Extends BorderPane  
 * @author Matthew Tribby
 */
public class LevelCreationPane extends BorderPane {
	public static final double DEFAULT_PREF_HEIGHT = 600.0;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	/**
	 * Passes in the DeveloperData, data used to construct game on front end and the pref height of the pane. 
	 * @param modelData
	 * @param prefHeight
	 */
	public LevelCreationPane(DeveloperData modelData, double prefHeight){
		super();
		this.setTop(createTitle());
		this.setCenter(new LevelCreatorHolder(modelData, prefHeight, BasicLevelCreator.class));
	}
	
	/**
	 * Same as above constructor except there is a pre-defined pref-height
	 * @param modelData Data which represent game in authoring environment
	 */
	public LevelCreationPane(DeveloperData modelData){
		this(modelData, DEFAULT_PREF_HEIGHT);
	}
	
	private Text createTitle(){
		Text title = new Text(myResources.getString("LEVEL_CREATION"));
		title.setFont(new Font(50));
		return title;
	}

}
