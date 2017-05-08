package gameauthorgui.inputhelpers;

import gameauthorgui.IntegerTextField;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * GUI component that takes in a String integer and has a given title
 * @author Matthew Tribby
 */
public class IntegerInputText extends HBox implements ParameterInput<String>{
	public static final String TYPE = "String";
	private String varName;
	private IntegerTextField input;
	
	/**
	 * Creates an integer input text field with the given variable name
	 * @param varName String name
	 */
	public IntegerInputText(String varName){
		super();
		this.varName = varName;
		createBox();
	}
	
	private void createBox(){
		input = new IntegerTextField();
		this.getChildren().addAll(new Text(varName), input);
	}
	
	/**
	 * Gets value of the text field
	 */
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
	/**
	 * Returns type of ParameterInput
	 */
	public String getType() {
		return TYPE;
	}
	
	
}
