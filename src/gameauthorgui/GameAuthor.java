package gameauthorgui;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * The GameAuthor gui is an abstract class meant to act as a basis for using the DeveloperStepOrganizer framework.
 * The way it works is that it creates a GUI based on a step-based process that the user can progress through.
 * The GameAuthor gui itself is non-genre specific and therefore can be made to create any game or process. To 
 * use your own extension, you just need to choose what you want to add and add them through the abstract
 * instantiateSteps method
 * @author Matthew Tribby
 */
public abstract class GameAuthor implements IGameAuthor {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	public static final int SCENE_WIDTH = 1200;
	public static final int SCENE_HEIGHT = 800;
	private StepOrganizer stepOrganizer;
	private Group currentStep;
	private Scene developerScene;
	private BorderPane view;
	
	/**
	 * Creates a GameAUthor and sets up the basics
	 */
	public GameAuthor() {	
		currentStep = new Group();
		view = new BorderPane();
		developerScene = new Scene(view, SCENE_WIDTH, SCENE_HEIGHT);
		stepOrganizer = new StepOrganizer(this);
		instantiate();
	}
	
	/**
	 * This method should be filled with addStep(DeveloperStep step) methods which will add new steps
	 * that the user creates to the GUI. This will form the basis of how the step-based GUI works
	 */
	public abstract void instantiateSteps();
	
	private void instantiate(){
		view.setLeft(stepOrganizer);
		view.setCenter(currentStep);
		view.setBottom(instantiateButtons());
	}
	
	private HBox instantiateButtons() {
		HBox buttons = new HBox(100);
		Button save = new Button(myResources.getString("save"));
		save.setOnAction(e -> save());
		buttons.getChildren().addAll(new PreviousStepButton(stepOrganizer), new NextStepButton(stepOrganizer), save);
		buttons.setAlignment(Pos.CENTER);
		return buttons;
	}

	/**
	 * Adds a step to the GUI
	 */
	@Override
	public void addStep(DeveloperStep step){
		addStep(stepOrganizer.getNumberSteps(), step);
	}
	
	/**
	 * Adds a step to the GUI at the specific index
	 */
	@Override
	public void addStep(int index, DeveloperStep step){
		stepOrganizer.addStep(index, step);
	}

	/**
	 * This changes the current step to this new step, thus changing the screen
	 */
	@Override
	public void changeStep(DeveloperStep step){
		currentStep.getChildren().clear();
		currentStep.getChildren().add(step.getStep());
	}
	
	/**
	 * Gets the scene to display on a stage
	 */
	@Override
	public Scene getScene() {
		return developerScene;
	}
	
	/**
	 * Returns the step  organizer
	 * @return
	 */
	public StepOrganizer getStepOrganizer(){
		return stepOrganizer;
	}
	
	/**
	 * Needed to save the data to xml
	 */
	public abstract void save();

}
