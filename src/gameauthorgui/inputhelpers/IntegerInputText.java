package gameauthorgui.inputhelpers;

import gameauthorgui.IntegerTextField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class IntegerInputText extends HBox implements ParameterInput<String>{
	public static final String TYPE = "String";
	private String varName;
	private IntegerTextField input;
	
	public IntegerInputText(String varName){
		super();
		this.varName = varName;
		createBox();
	}
	
	private void createBox(){
		input = new IntegerTextField();
		this.getChildren().addAll(new Text(varName), input);
	}
	
	public String getValue(){
		return input.getText();
	}
	
	public TextField getTextField(){
		return input;
	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
}
