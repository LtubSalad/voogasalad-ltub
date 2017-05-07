package gameauthorgui;

import javafx.scene.control.Button;

/**
 * Creates a button that goes to the previous step in the step organizer
 * @author Matthew Tribby
 */
public class PreviousStepButton extends Button{

	/**
	 * Creates the button for the step organizer that is passed in
	 * @param stepOrganizer
	 */
	public PreviousStepButton(StepOrganizer stepOrganizer){
		//TODO resource file
		super("Previous Step");
		this.setOnAction(e -> stepOrganizer.previousStep());
	}
}
