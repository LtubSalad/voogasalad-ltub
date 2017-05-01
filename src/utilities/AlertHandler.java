package utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertHandler {
	public Alert errorPopUp(String message){
		Alert errorAlert=new Alert(AlertType.ERROR);
		errorAlert.setHeaderText(message);
		return errorAlert;
	}
	
	public static  void showError(String message){
		Alert errorAlert=new Alert(AlertType.ERROR);
		errorAlert.setHeaderText(message);
		errorAlert.showAndWait();
	}
	
	public static void showMessage(String message){
		Alert messageAlert=new Alert(AlertType.INFORMATION);
		messageAlert.setHeaderText(message);
		messageAlert.showAndWait();
	}
	
	public Alert confirmationPopUp(String message){
		Alert confirmationAlert=new Alert(AlertType.CONFIRMATION);
		confirmationAlert.setHeaderText(message);
		return confirmationAlert;
	}
	
	
	
	

}
