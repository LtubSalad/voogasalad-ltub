package gameDevelopmentInterface.spriteCreator;


import java.io.File;

import exception.UnsupportedTypeException;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileVariableSetter extends VariableSetter<File>{

	public FileVariableSetter(String variableName) {
		super(variableName);
		Button fileChooser= new Button("Choose file");
		fileChooser.setOnMouseClicked((event)->{
			FileChooser chooser=new FileChooser();
			File file=chooser.showOpenDialog(new Stage());
			if(file!=null){
				
			}
		});
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public File getValue() throws UnsupportedTypeException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setField(File initialValue) {
		// TODO Auto-generated method stub
		
	}

}
