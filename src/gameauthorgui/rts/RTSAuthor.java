package gameauthorgui.rts;

import java.util.ResourceBundle;

import data.DeveloperData;
import gameDevelopmentInterface.GeneralDataCreator;
import gameDevelopmentInterface.ScreenModelCreator;
import gameDevelopmentInterface.spriteCreator.SpriteCreationScreen;
import gameauthorgui.DeveloperStep;
import gameauthorgui.GameAuthor;

public class RTSAuthor extends GameAuthor{
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private static final String PATH_TO_STYLE_SHEETS = "/styleSheets/MainStyle.css";
	private GeneralDataCreator myGeneralDataCreator;
	private DeveloperData myModelData;
	
	public RTSAuthor(){
		super();
		myGeneralDataCreator = new GeneralDataCreator();
		myModelData = new DeveloperData();
		getScene().getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
		instantiateSteps();
	}

	@Override
	public void instantiateSteps() {
		addStep(new DeveloperStep("Welcome", new RTSWelcomeScreen()));
		addStep(new DeveloperStep("Sprite creation",new SpriteCreationScreen(myModelData)));
		addStep(new DeveloperStep("General Data", myGeneralDataCreator));
		addStep(new DeveloperStep("Screen Setting", new ScreenModelCreator(myModelData.getSprites(),myGeneralDataCreator)));
	}

}
