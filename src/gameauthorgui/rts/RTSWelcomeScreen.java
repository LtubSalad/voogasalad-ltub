package gameauthorgui.rts;

import java.util.ResourceBundle;

import gameauthorgui.WelcomeScreen;

public class RTSWelcomeScreen extends WelcomeScreen {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);

	public RTSWelcomeScreen() {
		super(myResources.getString("RTS"));
	}	
}
