package gameauthorgui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class IntegerTextField extends TextField {

	public IntegerTextField(){
		super();
		setupNumberChecking();
	}
	
	//This method is only allows integers to be typed
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
