package gameauthorgui;

import javafx.scene.control.Button;

public class PreviousStepButton extends Button{

	public PreviousStepButton(StepOrganizer stepOrganizer){
		//TODO resource file
		super("Previous Step");
		this.setOnAction(e -> stepOrganizer.previousStep());
	}
}
