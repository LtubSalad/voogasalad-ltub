package gamecreation;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageParameterInput extends HBox implements ParameterInput{
	public static final String TYPE = "String";
	private String varName;
	private Button fileChoose;
	private String imagePath;
	
	
	public ImageParameterInput(String varName){
		super();
		this.varName = varName;
		createBox();
	}
	
	private void createBox(){
		//TODO resource file
		fileChoose = new Button("Select Image");
		//TODO check for image path
		fileChoose.setOnAction(e -> imagePath = new FileChooser().showOpenDialog(new Stage()).toString());
		this.getChildren().addAll(new Text(varName), fileChoose);
	}


	@Override
	public String getValue() {
		return imagePath;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	
}
