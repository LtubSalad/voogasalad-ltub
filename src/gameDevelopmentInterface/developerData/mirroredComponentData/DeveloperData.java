package gameDevelopmentInterface.developerData.mirroredComponentData;

import java.util.List;
import java.util.Map;

import gameDevelopmentInterface.Path;
import newengine.model.SpriteModel;

public class DeveloperData {
	private List<Path> myPaths;
	private Map<String, Sprite> mySprites;
	
	
	public List<Path> getPaths(){
		return myPaths;
	}

}
