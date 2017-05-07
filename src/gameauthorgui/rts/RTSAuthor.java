package gameauthorgui.rts;

import java.util.ResourceBundle;

import data.DeveloperData;
import data.ScreenModelData;
import gameDevelopmentInterface.GeneralDataCreator;
import gameDevelopmentInterface.ScreenModelCreator;
import gameDevelopmentInterface.spriteCreator.SpriteCreationScreen;
import gameauthorgui.DeveloperStep;
import gameauthorgui.GameAuthor;

/**
 * Implementation of Game Author, built as a basic version of an RTS game authoring environment
 * This was created as an example of the extensibility of our game GUI
 * @author Matthew Tribby
 */
public class RTSAuthor extends GameAuthor{
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private static final String PATH_TO_STYLE_SHEETS = "/styleSheets/MainStyle.css";
	private GeneralDataCreator myGeneralDataCreator;
	private DeveloperData myModelData;
	private ScreenModelData myScreenModelData;
	
	/**
	 * Creates the GUI with the steps that are made in instantiateSteps
	 */
	public RTSAuthor(){
		super();
		myModelData = new DeveloperData();
		myGeneralDataCreator = new GeneralDataCreator(myModelData);
		myScreenModelData = new ScreenModelData();
		getScene().getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
		instantiateSteps();
	}

	/**
	 * Adds the Developer steps which will be shown. DeveloperSteps are the different steps in the process
	 */
	@Override
	public void instantiateSteps() {
		addStep(new DeveloperStep("Welcome", new RTSWelcomeScreen(myModelData)));
		addStep(new DeveloperStep("Sprite creation",new SpriteCreationScreen(myModelData)));
		addStep(new DeveloperStep("General Data", myGeneralDataCreator));
	}

	/**
	 * Currently not implemented but will save the game state of the RTS author to transport to back-end
	 */
	@Override
	public void save() {
		
	}

}
