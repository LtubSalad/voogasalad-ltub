package gameauthorgui.tower;

import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import data.DeveloperData;
import data.ScreenModelData;
import data.SerializableDeveloperData;
import gameDevelopmentInterface.BackgroundSetter;
import gameDevelopmentInterface.GeneralDataCreator;
import gameDevelopmentInterface.PathCreator;
import gameDevelopmentInterface.ScreenModelCreator;
import gameDevelopmentInterface.spriteCreator.SpriteCreationEnvironment;
import gameauthorgui.DeveloperStep;
import gameauthorgui.GameAuthor;
import gameauthorgui.WelcomeScreen;
import gamecreation.level.LevelCreationPane;
import utilities.XStreamHandler;

/**
 * 
 */
public class TowerAuthor extends GameAuthor {
	private static final String SET_THE_BACKGROUND = "Set the background";
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private static final String SCREEN_SETTING = "SCREEN_SETTING";
	private static final String GENERAL_DATA = "GENERAL_DATA";
	private static final String PATH_TO_STYLE_SHEETS = "/styleSheets/MainStyle.css";
	public static final int CENTER_OFFSETS = 200;
	private GeneralDataCreator myGeneralDataCreator;
	private DeveloperData myModelData;
	private ScreenModelData myScreenModelData;

	public TowerAuthor() {
		super();
		myModelData=new DeveloperData();
		myGeneralDataCreator = new GeneralDataCreator(myModelData);
		//myScreenModelData = new ScreenModelData();
		getScene().getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
		instantiateSteps();
	}

	public void instantiateSteps() {
		addStep(new DeveloperStep("Welcome", new WelcomeScreen("Tower Defense", myModelData)));

		addStep(new DeveloperStep("Level Options",
				new LevelCreationPane(myModelData, getScene().getHeight() - CENTER_OFFSETS)));
//		addStep(new DeveloperStep(SET_THE_BACKGROUND,
//				new BackgroundSetter(myModelData.getScreenSprites(), myGeneralDataCreator, myScreenModelData)));
		addStep(new DeveloperStep("Path Creation", new PathCreator(myModelData)));
		addStep(new DeveloperStep("Sprite creation", new SpriteCreationEnvironment(myModelData)));
		addStep(new DeveloperStep("Spawner Creation", new SpawnerCreationScreen(myModelData)));
		addStep(new DeveloperStep(myResources.getString(GENERAL_DATA), myGeneralDataCreator));
		//addStep(new DeveloperStep(myResources.getString(SCREEN_SETTING),
			//	new ScreenModelCreator(myModelData.getScreenSprites(), myGeneralDataCreator, myScreenModelData)));
	}

	@Override
	public void save() {
		SerializableDeveloperData data = new SerializableDeveloperData(myModelData);
		XStreamHandler XSH = new XStreamHandler();
		XSH.saveToFile(data);
	}

}