package gameauthorgui.tower;

import java.util.ResourceBundle;

import gameauthorgui.WelcomeScreen;


public class TowerWelcomeScreen extends WelcomeScreen{
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	public static final String WELCOME_KEY = "TOWER_DEFENSE";

	public TowerWelcomeScreen() {
		super(myResources.getString(WELCOME_KEY));
	}
}