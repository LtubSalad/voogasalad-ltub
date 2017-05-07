package gameauthorgui.inputhelpers;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Links a button with a string input. Essentially the result will update when the button is pressed
 * @author Matthew Tribby
 */
public class ButtonStringParameterInput extends HBox{
	private StringParameterInput text;
	private Button done;
	
	/**
	 * Creates a string parameter input with the given title
	 * @param varName
	 */
	public ButtonStringParameterInput(String varName) {
		super();
		this.text = new StringParameterInput(varName);
		this.done = new Button("Done");
		this.getChildren().addAll(text, done);
	}
	
	/**
	 * Returns the button, helpful for setting action on the button
	 * @return Button
	 */
	public Button getDoneButton(){
		return done;
	}
	
	/**
	 * Gets the value of the String
	 * @return String
	 */
	public String getText(){
		return text.getValue();
	}
}
