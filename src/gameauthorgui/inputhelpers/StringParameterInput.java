package gameauthorgui.inputhelpers;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * ParameterInput that takes in String
 * @author Matthew Tribby
 */
public class StringParameterInput extends HBox implements ParameterInput<String>{
	public static final String TYPE = "String";
	private String varName;
	private TextField input;
	
	/**
	 * Gives a title to the StringParameterInput
	 * @param varName 
	 */
	public StringParameterInput(String varName){
		super();
		this.varName = varName;
		createBox();
	}
	
	private void createBox(){
		input = new TextField();
		this.getChildren().addAll(new Text(varName), input);
	}
	
	/**
	 * Gets value of the parameter input 
	 */
	public String getValue(){
		return input.getText();
	}
	
	/**
	 * Gets the text property to add listeners to the parameter input
	 * @return StringProperty
	 */
	public StringProperty getTextProperty(){
		return input.textProperty();
	}

	@Override
	/**
	 * Gets type of the parameter input
	 */
	public String getType() {
		return TYPE;
	}
	
	
}
