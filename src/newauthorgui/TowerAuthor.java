package newauthorgui;
import java.util.ResourceBundle;

import data.DeveloperData;
import gameDevelopmentInterface.GeneralDataCreator;
import gameDevelopmentInterface.ScreenModelCreator;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 */
public class TowerAuthor {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private static final String SCREEN_SETTING = "SCREEN_SETTING";
	private static final String GENERAL_DATA = "GENERAL_DATA";
	private static final String PATH_TO_STYLE_SHEETS = "/styleSheets/MainStyle.css";
	private Scene developerScene;
	private BorderPane view;
	private Group currentStep;
	private StepOrganizer developerSteps;
	private GeneralDataCreator myGeneralDataCreator = new GeneralDataCreator();
	private DeveloperData myModelData;
	private Stage towerStage;
	
	public TowerAuthor() {
		towerStage = new Stage();
		
		myModelData=new DeveloperData();
		currentStep = new Group();
		instantiate();
		developerScene = new Scene(view);
		developerScene.getStylesheets().setAll(PATH_TO_STYLE_SHEETS);
		
		towerStage.setScene(developerScene);
		towerStage.show();
	}
	
	private void instantiate() {
		view = new BorderPane();
		currentStep.getChildren().add(new WelcomeTowerScreen());
		instantiateSteps();
		view.setLeft(developerSteps);
		view.setCenter(currentStep);
	}
	
	private void instantiateSteps() {
		developerSteps = new StepOrganizer();
		addStep(new DeveloperStep("Welcome", new WelcomeTowerScreen()));
		addStep(new DeveloperStep("Level Options", new LevelOptionsSelector()));
		addStep(new DeveloperStep("Sprite creation",new SpriteCreatorPane(myModelData)));
		addStep(new DeveloperStep(myResources.getString(GENERAL_DATA), myGeneralDataCreator));
		addStep(new DeveloperStep(myResources.getString(SCREEN_SETTING), new ScreenModelCreator(myModelData.getSprites(),myGeneralDataCreator)));
	}
	
	public void addStep(DeveloperStep step){
		addStep(developerSteps.getNumberSteps(), step);
	}
	
	public void addStep(int index, DeveloperStep step){
		developerSteps.addStep(index, step);
		step.setOnMouseClicked(e -> {
			currentStep.getChildren().clear();
			currentStep.getChildren().add(step.getStep());
		});
	}
	
	
	public Scene getScene() {
		return developerScene;
	}
}