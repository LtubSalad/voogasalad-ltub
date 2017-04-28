package gameauthorgui.rts;

import java.util.ResourceBundle;

import data.DeveloperData;
import data.ScreenModelData;
import gameDevelopmentInterface.GeneralDataCreator;
import gameDevelopmentInterface.ScreenModelCreator;
import gameauthorgui.DeveloperStep;
import gameauthorgui.GameAuthor;
import gameauthorgui.tower.SpriteCreatorPane;

public class RTSAuthor extends GameAuthor{
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private static final String PATH_TO_STYLE_SHEETS = "/styleSheets/MainStyle.css";
	private GeneralDataCreator myGeneralDataCreator;
	private DeveloperData myModelData;
	private ScreenModelData myScreenModelData;
	
	public RTSAuthor(){
		super();
		myGeneralDataCreator = new GeneralDataCreator();
		myModelData = new DeveloperData();
		myScreenModelData = new ScreenModelData();
		getScene().getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
		instantiateSteps();
	}

	@Override
	public void instantiateSteps() {
		addStep(new DeveloperStep("Welcome", new RTSWelcomeScreen()));
		addStep(new DeveloperStep("Sprite creation",new SpriteCreatorPane(myModelData)));
		addStep(new DeveloperStep("General Data", myGeneralDataCreator));
		addStep(new DeveloperStep("Screen Setting", new ScreenModelCreator(myModelData.getScreenSprites(),myGeneralDataCreator, myScreenModelData )));
	}

}
