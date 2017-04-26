package gameauthorgui.tower;
import java.util.ResourceBundle;

import data.DeveloperData;
import gameDevelopmentInterface.GeneralDataCreator;
import gameDevelopmentInterface.ScreenModelCreator;
import gameauthorgui.DeveloperStep;
import gameauthorgui.GameAuthor;
import gameauthorgui.WelcomeScreen;

/**
 * 
 */
public class TowerAuthor extends GameAuthor {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private static final String SCREEN_SETTING = "SCREEN_SETTING";
	private static final String GENERAL_DATA = "GENERAL_DATA";
	private static final String PATH_TO_STYLE_SHEETS = "/styleSheets/MainStyle.css";
	private GeneralDataCreator myGeneralDataCreator;
	private DeveloperData myModelData;
	
	public TowerAuthor() {	
		super();
		myModelData=new DeveloperData();
		myGeneralDataCreator = new GeneralDataCreator();
		getScene().getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
		instantiateSteps();
	}
	
	public void instantiateSteps() {
		addStep(new DeveloperStep("Welcome", new WelcomeScreen("Tower Defense")));
		addStep(new DeveloperStep("Level Options", new LevelOptionsSelector()));
		addStep(new DeveloperStep("Sprite creation",new SpriteCreatorPane(myModelData)));
		addStep(new DeveloperStep(myResources.getString(GENERAL_DATA), myGeneralDataCreator));
		addStep(new DeveloperStep(myResources.getString(SCREEN_SETTING), new ScreenModelCreator(myModelData.getSprites(),myGeneralDataCreator)));
	}
	
}