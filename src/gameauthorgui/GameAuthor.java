package gameauthorgui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public abstract class GameAuthor implements IGameAuthor {
	public static final int SCENE_WIDTH = 1200;
	public static final int SCENE_HEIGHT = 800;
	private StepOrganizer stepOrganizer;
	private Group currentStep;
	private Scene developerScene;
	private BorderPane view;
	
	public GameAuthor() {	
		currentStep = new Group();
		view = new BorderPane();
		developerScene = new Scene(view, SCENE_WIDTH, SCENE_HEIGHT);
		stepOrganizer = new StepOrganizer(this);
		instantiate();
	}
	
	public abstract void instantiateSteps();
	
	private void instantiate(){
		view.setLeft(stepOrganizer);
		view.setCenter(currentStep);
		view.setBottom(instantiateButtons());
	}
	
	private HBox instantiateButtons() {
		HBox buttons = new HBox(100);
		buttons.getChildren().addAll(new PreviousStepButton(stepOrganizer), new NextStepButton(stepOrganizer));
		buttons.setAlignment(Pos.CENTER);
		return buttons;
	}

	@Override
	public void addStep(DeveloperStep step){
		addStep(stepOrganizer.getNumberSteps(), step);
	}
	
	@Override
	public void addStep(int index, DeveloperStep step){
		stepOrganizer.addStep(index, step);
	}

	@Override
	public void changeStep(DeveloperStep step){
		currentStep.getChildren().clear();
		currentStep.getChildren().add(step.getStep());
	}
	
	@Override
	public Scene getScene() {
		return developerScene;
	}
	
	public StepOrganizer getStepOrganizer(){
		return stepOrganizer;
	}

}
