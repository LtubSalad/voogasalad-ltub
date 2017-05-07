package gameauthorgui;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * StepOrganizer is meant to hold DeveloperStep objects. It organizes the objects in a central place. The goal of
 * this to act as a foundation for a step-based creation process, which links a GUI step to a node that will
 * fill the screen
 * @author Matthew Tribby
 */
public class StepOrganizer extends VBox {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private List<DeveloperStep> steps;
	private VBox stepHolder;
	private int currentIndex;
	private int nextIndex;
	private IGameAuthor author;
	
	/**
	 * Creates a StepOrganizer linked to an IGameAuthor environment which sets forward the GUI's relationship
	 * with the StepOrganizer
	 * @param author
	 */
	public StepOrganizer(IGameAuthor author){
		super();
		this.author = author;
		this.steps = new ArrayList<DeveloperStep>();
		this.setAlignment(Pos.CENTER);
		createTitle();
		currentIndex = 0;
		nextIndex = 0;
	}
	
	private void updateStep() {
		steps.get(currentIndex).setBaseColor();
		steps.get(nextIndex).highlight();
		author.changeStep(steps.get(nextIndex));
		currentIndex = nextIndex;
	}

	private void createTitle(){
		Text stepTitle = new Text(myResources.getString("CREATION_STEPS"));
		stepTitle.setFont(new Font(20));
		stepHolder = new VBox();
		this.getChildren().addAll(stepTitle, stepHolder);
	}
	
	/**
	 * Adds a list of steps to the organizer
	 * @param steps DeveloperSteps to add
	 */
	public void addSteps(List<DeveloperStep> steps){
		steps.stream().forEach(e -> addStep(e));
	}
	
	/**
	 * Adds a single step to the organizer
	 * @param step DeveloperStep
	 */
	public void addStep(DeveloperStep step){
		addStep(this.getChildren().size(), step);
	}
	
	/**
	 * Adds step at a certain index
	 * @param index
	 * @param step DeveloperSteps
	 */
	public void addStep(int index, DeveloperStep step){
		stepHolder.getChildren().add(index, step);
		steps.add(index, step);
		if(steps.size() == 1){
			updateStep();
		}
		step.setOnMouseClicked(e -> {
			nextIndex = steps.indexOf(step);
			updateStep();
		});
	}
	
	/**
	 * Returns a list of steps in the organizer
	 * @return the steps
	 */
	public List<DeveloperStep> getSteps(){
		return steps;
	}
	
	/**
	 * Gets the number of steps
	 * @return number of steps
	 */
	public int getNumberSteps(){
		return steps.size();
	}
	
	/**
	 * Progresses to the next step
	 */
	public void nextStep(){
		if(currentIndex != steps.size() -1){
			nextIndex = currentIndex + 1;
			updateStep();
		}
	}
	
	/**
	 * Goes back to the previous step
	 */
	public void previousStep(){
		if(currentIndex != 0){
			nextIndex = currentIndex - 1;
			updateStep();
		}
	}
	
	/**
	 * Set the base color for all steps inside
	 */
	public void setBaseColor(){
		steps.stream().forEach(e -> e.setBaseColor());
	}

}
