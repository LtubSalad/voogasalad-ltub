package gameauthorgui.tower;
import java.util.ResourceBundle;

import data.DeveloperData;
import data.ScreenModelData;
import gameDevelopmentInterface.BackgroundSetter;
import gameDevelopmentInterface.GeneralDataCreator;
import gameDevelopmentInterface.ScreenModelCreator;
import gameauthorgui.DeveloperStep;
import gameauthorgui.GameAuthor;
import gameauthorgui.NextStepButton;
import gameauthorgui.PreviousStepButton;
import gameauthorgui.StepOrganizer;
import gameauthorgui.WelcomeScreen;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
	public static final int SCENE_WIDTH = 1200;
	public static final int SCENE_HEIGHT = 800;
	private Scene developerScene;
	private BorderPane view;
	private Group currentStep;
	private StepOrganizer developerSteps;
	private GeneralDataCreator myGeneralDataCreator = new GeneralDataCreator();
	private DeveloperData myModelData;
	private ScreenModelData myScreenData;
	private Stage towerStage;
	
	public TowerAuthor() {
		towerStage = new Stage();
		myScreenData = new ScreenModelData();
		myScreenData.getAllObjectsOnScreen();
	
		myModelData=new DeveloperData();
		currentStep = new Group();
		instantiate();
		developerScene = new Scene(view, SCENE_WIDTH, SCENE_HEIGHT);
		developerScene.getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
		
		towerStage.setScene(developerScene);
		//towerStage.show();
	}
	
	private void instantiate() {
		view = new BorderPane();
		currentStep.getChildren().add(new WelcomeScreen("Tower"));
		instantiateSteps();
		view.setLeft(developerSteps);
		view.setCenter(currentStep);
		view.setBottom(instantiateButtons());
	}

	public void instantiateSteps() {
		developerSteps = new StepOrganizer(this);
		addStep(new DeveloperStep("Welcome", new WelcomeScreen("Tower")));
		addStep(new DeveloperStep("Level Options", new LevelOptionsSelector()));
		addStep(new DeveloperStep(SET_THE_BACKGROUND, new BackgroundSetter(myModelData.getScreenSprites(), myGeneralDataCreator, myScreenData)));
		addStep(new DeveloperStep("Sprite creation",new SpriteCreatorPane(myModelData)));
		addStep(new DeveloperStep(myResources.getString(GENERAL_DATA), myGeneralDataCreator));
		addStep(new DeveloperStep(myResources.getString(SCREEN_SETTING), new ScreenModelCreator(myModelData.getScreenSprites(), myGeneralDataCreator, myScreenData)));
	}
	
	private HBox instantiateButtons() {
		HBox buttons = new HBox(100);
		buttons.getChildren().addAll(new PreviousStepButton(developerSteps), new NextStepButton(developerSteps));
		buttons.setAlignment(Pos.CENTER);
		return buttons;
	}
	
	public void addStep(DeveloperStep step){
		addStep(developerSteps.getNumberSteps(), step);
	}
	
	public void addStep(int index, DeveloperStep step){
		developerSteps.addStep(index, step);
	}
	
	public void changeStep(DeveloperStep step){
		currentStep.getChildren().clear();
		currentStep.getChildren().add(step.getStep());
	}

	public Scene getScene() {
		return developerScene;
	}
}
