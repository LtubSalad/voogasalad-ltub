package gameauthorgui.rts;

import java.util.ResourceBundle;

import data.DeveloperData;
import gameauthorgui.WelcomeScreen;

/**
 * Extension of Welcome screen which will be for an RTS screen
 * @author Matthew Tribby
 */
public class RTSWelcomeScreen extends WelcomeScreen {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);

	public RTSWelcomeScreen(DeveloperData modelData) {
		super(myResources.getString("RTS"), modelData);
	}	
}
