package gameDevelopmentInterface.presetHandling;

import data.DeveloperData;
import data.SpriteMakerModel;
import gameDevelopmentInterface.spriteCreator.SpriteCreationScreen;

public class SpriteEditorLoader {
	public SpriteCreationScreen loadSavedModel(SpriteMakerModel model, DeveloperData data){
		SpriteCreationScreen screen=new SpriteCreationScreen(data);
		model.getComponents().values().forEach((component)->{
			screen.addComponent(component);
		});
		return screen;
	}
	
	public SpriteCreationScreen loadPresetModel(SpriteMakerModel model, DeveloperData data){
		SpriteCreationScreen screen=new SpriteCreationScreen(data);
		model.getComponents().values().forEach((component)->{
			//make it unremovable
			screen.addComponent(component);
		});
		return screen;
	}
}
