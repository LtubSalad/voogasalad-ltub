package gameauthorgui.inputhelpers;

import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * ParameterInput made to take in images
 * @author Matthew Tribby
 */
public class ImageParameterInput extends HBox implements ParameterInput<String>{
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	public static final String TYPE = "String";
	private String varName;
	private Button fileChoose;
	private TextField imagePath;
	
	/**
	 * Creates a image parameter input with the given variable name
	 * @param varName
	 */
	public ImageParameterInput(String varName){
		super();
		imagePath = new TextField();
		this.varName = varName;
		createBox();
	}
	
	private void createBox(){
		//TODO resource file
		fileChoose = new Button(myResources.getString("IMAGE_CHOOSE"));
		//TODO check for image path
		fileChoose.setOnAction(e -> imagePath.setText(new FileChooser().showOpenDialog(new Stage()).toString()));
		this.getChildren().addAll(new Text(varName), fileChoose);
	}


	@Override
	/**
	 * Gets path of image in string
	 */
	public String getValue() {
		return imagePath.getText();
	}
	
	/**
	 * Gets text property to connect to listeners
	 * @return StringProperty
	 */
	public StringProperty getTextProperty(){
		return imagePath.textProperty();
	}
	
	
	@Override
	/**
	 * Gets type of ParameterInput
	 */
	public String getType() {
		return TYPE;
	}

	
}
