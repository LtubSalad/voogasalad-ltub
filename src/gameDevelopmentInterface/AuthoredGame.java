package gameDevelopmentInterface;

import data.DeveloperData;
import data.ScreenModelData;

public class AuthoredGame {
	private DeveloperData myGeneralData;
	private ScreenModelData myScreenData;
	private PathCreator myPaths;

	public AuthoredGame() {
		myGeneralData = new DeveloperData();
		myScreenData = new ScreenModelData();
		myPaths = new PathCreator(null);
	}

	public DeveloperData getGeneralData() {
		return myGeneralData;
	}

	public ScreenModelData getScreenData() {
		return myScreenData;
	}

	public PathCreator getPaths() {
		return myPaths;
	}
}