package gameauthorgui.inputhelpers;

import gameauthorgui.IntegerTextField;
import javafx.beans.property.StringProperty;
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
	
	/**
	 * This method is used in order to bind listeners to the input
	 * @return String property
	 */
	public StringProperty getTextProperty(){
		return input.textProperty();
	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
}
