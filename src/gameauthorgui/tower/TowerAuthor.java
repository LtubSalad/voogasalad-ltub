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
 * This is the GUI for a Tower Defense Authoring Environment
 * @author Matthew Tribby
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

	/**
	 * Instantiates the Tower Authoring Environment and adds the steps in instantiateSteps
	 */
	public TowerAuthor() {
		super();
		myModelData=new DeveloperData();
		myGeneralDataCreator = new GeneralDataCreator(myModelData);
		getScene().getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
		instantiateSteps();
	}
	
	/**
	 * Returns the data which is stored within the authoring environment for transportation
	 * @return DeveloperData
	 */
	public DeveloperData getData() {
		return myModelData;
	}

	/**
	 * Adds steps to the GUI
	 */
	public void instantiateSteps() {
		addStep(new DeveloperStep(myResources.getString("WELCOME"), new WelcomeScreen(myResources.getString("towerDefense"), myModelData)));

		addStep(new DeveloperStep(myResources.getString("LEVEL_CREATION"),
				new LevelCreationPane(myModelData, getScene().getHeight() - CENTER_OFFSETS)));
		addStep(new DeveloperStep(myResources.getString("PATH_CREATION"), new PathCreator(myModelData)));
		addStep(new DeveloperStep(myResources.getString("SPRITE_CREATION"), new SpriteCreationEnvironment(myModelData)));
		addStep(new DeveloperStep(myResources.getString("SPAWNER_CREATION"), new SpawnerCreationScreen(myModelData)));
		addStep(new DeveloperStep(myResources.getString(GENERAL_DATA), myGeneralDataCreator));
	}

	/**
	 * Saves the current state to XML
	 */
	@Override
	public void save() {
		SerializableDeveloperData data = new SerializableDeveloperData(myModelData);
		XStreamHandler XSH = new XStreamHandler();
		XSH.saveToFile(data);
	}

}