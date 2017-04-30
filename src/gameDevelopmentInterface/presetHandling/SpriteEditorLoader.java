package gameDevelopmentInterface.presetHandling;

import data.DeveloperData;
import data.SpriteMakerModel;
import gameDevelopmentInterface.spriteCreator.SpriteCreationScreen;

public class SpriteEditorLoader {
	public SpriteCreationScreen loadModel(SpriteMakerModel model, DeveloperData data){
		SpriteCreationScreen screen=new SpriteCreationScreen(data);
		model.getDeprecatedComponents().values().forEach((component)->{
			screen.getInfoPane().addComponent(component,true);
		});
		return screen;
	}
	
	public SpriteCreationScreen loadModelPreset(SpriteMakerModel model, DeveloperData data){
		SpriteCreationScreen screen=new SpriteCreationScreen(data);
		model.getDeprecatedComponents().values().forEach((component)->{
			screen.getInfoPane().addComponent(component,false);
		});
		return screen;
	}
}
