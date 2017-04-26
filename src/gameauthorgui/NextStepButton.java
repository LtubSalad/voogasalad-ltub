package gameauthorgui;

import javafx.scene.control.Button;

public class NextStepButton extends Button{

	public NextStepButton(StepOrganizer stepOrganizer){
		//TODO resource file
		super("Next Step");
		this.setOnAction(e -> stepOrganizer.nextStep());
	}
}
