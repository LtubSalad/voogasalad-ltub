package gameauthorgui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * This class is an extension of TextField only allows integers to be typed into, useful for controlling user
 * inputs
 * @author Matthew Tribby
 */
public class IntegerTextField extends TextField {

	/**
	 * Creates an integer text field with no specific characteristics
	 */
	public IntegerTextField(){
		super();
		setupNumberChecking();
	}
	
	//This entire method was found on stack overflow: http://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
	private void setupNumberChecking(){
		this.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            if (!newValue.matches("\\d*")) {
	                IntegerTextField.this.setText(newValue.replaceAll("[^\\d]", ""));
	            }
	        }
	    });
	}

	
	
}
