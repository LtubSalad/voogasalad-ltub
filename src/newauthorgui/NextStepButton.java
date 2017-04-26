package newauthorgui;

import javafx.scene.control.Button;

public class NextStepButton extends Button{

	NextStepButton(StepOrganizer stepOrganizer){
		//TODO resource file
		super("Next Step");
		this.setOnAction(e -> stepOrganizer.nextStep());
	}
}
