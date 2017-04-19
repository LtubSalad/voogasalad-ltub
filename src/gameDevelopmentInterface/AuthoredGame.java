package gameDevelopmentInterface;

import data.GeneralModelData;
import data.ScreenModelData;

public class AuthoredGame {
	private GeneralModelData myGeneralData;
	private ScreenModelData myScreenData;
	private PathCreator myPaths;

	public AuthoredGame() {
		myGeneralData = new GeneralModelData();
		myScreenData = new ScreenModelData();
		myPaths = new PathCreator(null);
	}

	public GeneralModelData getGeneralData() {
		return myGeneralData;
	}

	public ScreenModelData getScreenData() {
		return myScreenData;
	}

	public PathCreator getPaths() {
		return myPaths;
	}
}