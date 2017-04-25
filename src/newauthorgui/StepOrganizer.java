package newauthorgui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StepOrganizer extends VBox {
	private List<DeveloperStep> steps;
	private VBox stepHolder;
	private int currentIndex;
	private int nextIndex;
	private GameAuthor author;
	
	public StepOrganizer(GameAuthor author){
		super();
		this.author = author;
		this.steps = new ArrayList<DeveloperStep>();
		createBasic();
		currentIndex = 0;
		nextIndex = 0;
	}
	
	private void updateStep() {
		steps.get(currentIndex).setBaseColor();
		steps.get(nextIndex).highlight();
		author.changeStep(steps.get(nextIndex));
		currentIndex = nextIndex;
	}

	private void createBasic(){
		Text stepTitle = new Text("Creation Steps");
		stepHolder = new VBox();
		this.getChildren().addAll(stepTitle, stepHolder);
	}
	
	public void addSteps(List<DeveloperStep> steps){
		steps.stream().forEach(e -> addStep(e));
	}
	
	public void addStep(DeveloperStep step){
		addStep(this.getChildren().size(), step);
	}
	
	public void addStep(int index, DeveloperStep step){
		stepHolder.getChildren().add(index, step);
		steps.add(index, step);
		step.setOnMouseClicked(e -> {
			nextIndex = steps.indexOf(step);
			updateStep();
		});
	}
	
	public List<DeveloperStep> getSteps(){
		return steps;
	}
	
	public int getNumberSteps(){
		return steps.size();
	}
	
	public void nextStep(){
		if(currentIndex != steps.size() -1){
			nextIndex = currentIndex++;
			updateStep();
		}
	}
	
	public void previousStep(){
		if(currentIndex != 0){
			nextIndex = currentIndex--;
			updateStep();
		}
	}
	
	public void setBaseColor(){
		steps.stream().forEach(e -> e.setBaseColor());
	}

	

}
