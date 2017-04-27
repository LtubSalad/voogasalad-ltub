package newauthorgui;

import javafx.scene.control.Button;

public class PreviousStepButton extends Button{

	PreviousStepButton(StepOrganizer stepOrganizer){
		//TODO resource file
		super("Previous Step");
		this.setOnAction(e -> stepOrganizer.previousStep());
	}
}
