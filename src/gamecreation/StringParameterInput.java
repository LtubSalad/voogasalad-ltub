package gamecreation;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class StringParameterInput implements ParameterInput{
	public static final String TYPE = "String";
	private String varName;
	private HBox complete;
	private TextField input;
	
	StringParameterInput(String varName){
		this.varName = varName;
		createBox();
	}
	
	private void createBox(){
		complete = new HBox();
		input = new TextField();
		complete.getChildren().addAll(new Text(varName), input);
	}
	
	public Node get(){
		return complete;
	}
	
	public String getValue(){
		return input.getText();
	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
}
