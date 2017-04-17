#These are the pertinent APIs that allow us to function between sub-teams

#Game Engine

#Game Authoring Environment

The only API Jake needs to provide for the Game Data subteam is in the class AuthoredGame

	public AuthoredGame() {
		myGeneralData = new GeneralModelData();
		myScreenData = new ScreenModelData();
		myPaths = new PathCreator();
	}
	
	public GeneralModelData getGeneralData() {
	}
	
	public ScreenModelData getScreenData() {
	}
	
	public PathCreator getPaths() {
	}

This class will be "xstreamed" into an xml file that the Game Data team can read out
back into an AuthoredGame class and get all necessary components of a game.

#Game Player

#Game Data