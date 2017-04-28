package gameDevelopmentInterface.spriteCreator;


import java.io.File;

import exception.UnsupportedTypeException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileVariableSetter extends VariableSetter<File>{
	private File selectedFile;
	
	public FileVariableSetter(String variableName) {
		super(variableName);
		Button fileChooser= new Button("Choose file");
		Label label=new Label("No file chosen");
		fileChooser.setOnMouseClicked((event)->{
			FileChooser chooser=new FileChooser();
			File file=chooser.showOpenDialog(new Stage());
			if(file!=null){
				selectedFile=file;
				label.setText(selectedFile.getName());
			}
		});
		this.getChildren().addAll(fileChooser,label);
		// TODO Auto-generated constructor stub
	}

	@Override
	public File getValue() throws UnsupportedTypeException, Exception {
		// TODO Auto-generated method stub
		return selectedFile;
	}

	@Override
	public void setField(File initialValue) {
		selectedFile=initialValue;
		
	}

}
