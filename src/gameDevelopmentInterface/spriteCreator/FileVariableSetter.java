package gameDevelopmentInterface.spriteCreator;


import java.io.File;

import exception.UnsupportedTypeException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileVariableSetter extends VariableSetter<File>{
	private File selectedFile;
	private final File baseDirectory =new File(System.getProperty("user.dir")+File.separator+"data");
	
	public FileVariableSetter(String variableName) {
		super(variableName);
		Button fileChooser= new Button("Choose file");
		Label label=new Label("No file chosen");
		FileChooser chooser=new FileChooser();
		chooser.setInitialDirectory(baseDirectory);
		fileChooser.setOnMouseClicked((event)->{
			File file=chooser.showOpenDialog(new Stage());
			if(file!=null){
				File relativePath=new File(baseDirectory.toURI().relativize(file.toURI()).getPath());
				selectedFile=relativePath;
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
