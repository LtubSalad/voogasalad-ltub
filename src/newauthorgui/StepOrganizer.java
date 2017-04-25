package newauthorgui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StepOrganizer extends VBox {
	private List<DeveloperStep> steps;
	private VBox stepHolder;
	
	public StepOrganizer(){
		super();
		createBasic();
		steps = new ArrayList<DeveloperStep>();
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
	}
	
	public List<DeveloperStep> getSteps(){
		return steps;
	}
	
	public int getNumberSteps(){
		return steps.size();
	}

	

}
