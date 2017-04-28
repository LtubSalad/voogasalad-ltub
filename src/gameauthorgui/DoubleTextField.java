package gameauthorgui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class DoubleTextField extends TextField{
	boolean decimalEntered = false;
	
	public DoubleTextField(){
		super();
		setUpNumberChecking();
	}

	private void setUpNumberChecking(){
		this.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	        	//TODO not actually working yet
	        }
	    });
	}
}
