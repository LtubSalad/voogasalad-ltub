package gamecreation;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class StringParameterInput extends HBox implements ParameterInput{
	public static final String TYPE = "String";
	private String varName;
	private TextField input;
	
	public StringParameterInput(String varName){
		super();
		this.varName = varName;
		createBox();
	}
	
	private void createBox(){
		input = new TextField();
		this.getChildren().addAll(new Text(varName), input);
	}
	
	public String getValue(){
		return input.getText();
	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
}
