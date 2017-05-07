package gameauthorgui;

import javafx.scene.control.Button;

/**
 * Button made that will progress the StepOrganizer to the next step
 * @author Matthew Tribby
 */
public class NextStepButton extends Button{

	/**
	 * Creates the button and links it to a step organizer
	 * @param stepOrganizer
	 */
	public NextStepButton(StepOrganizer stepOrganizer){
		//TODO resource file
		super("Next Step");
		this.setOnAction(e -> stepOrganizer.nextStep());
	}
}
