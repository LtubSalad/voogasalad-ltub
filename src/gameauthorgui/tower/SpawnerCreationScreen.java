package gameauthorgui.tower;

import data.DeveloperData;
import gameDevelopmentInterface.SpawnerCreation;
import gamecreation.level.SpawnerLevelEditorHolder;
import javafx.scene.layout.BorderPane;

/**
 * Pane which holds the contents of the SpawnerCreationScreen
 * @author Matthew Tribby
 */
public class SpawnerCreationScreen extends BorderPane {
	private DeveloperData modelData;
	
	public SpawnerCreationScreen(DeveloperData modelData){
		super();
		this.modelData = modelData;
		this.setCenter(new SpawnerCreation(modelData));
	}
}