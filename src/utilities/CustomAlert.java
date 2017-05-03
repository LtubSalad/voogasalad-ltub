package utilities;

import javafx.scene.control.Alert;

public class CustomAlert extends Alert {

	public CustomAlert(AlertType alertType, String text) {
		super(alertType);
		this.setTitle(text);
		this.setContentText(text);
	}
	
	

}
