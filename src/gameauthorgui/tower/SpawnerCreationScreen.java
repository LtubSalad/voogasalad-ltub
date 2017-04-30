package gameauthorgui.tower;

import data.DeveloperData;
import gameDevelopmentInterface.SpawnerCreation;
import gamecreation.level.SpawnerLevelEditorHolder;
import javafx.scene.layout.BorderPane;

public class SpawnerCreationScreen extends BorderPane {
	private DeveloperData modelData;
	
	public SpawnerCreationScreen(DeveloperData modelData){
		super();
		this.modelData = modelData;
		//this.setRight(new SpawnerLevelEditorHolder(modelData.getLevelData(), 600));
		this.setCenter(new SpawnerCreation(modelData));
	}

}