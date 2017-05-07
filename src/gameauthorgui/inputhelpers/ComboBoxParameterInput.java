package gameauthorgui.inputhelpers;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * ParameterInput for a ComboBox
 * @author Matthew Tribby
 */
public class ComboBoxParameterInput extends HBox implements ParameterInput<String>{
	public static final String TYPE = "String";
	public static final int TEXT_INPUT_MAX_WIDTH = 100;
	private String varName;
	private ComboBox<String> combo;
	private StringParameterInput text;
	private boolean textAppended = false;
	
	/**
	 * Creates the combo box and gives it a title 
	 * @param varName Name of variable
	 * @param options Options of combo box
	 */
	public ComboBoxParameterInput(String varName, List<String> options){
		super();
		this.varName = varName;
		createText();
		text.setMaxWidth(TEXT_INPUT_MAX_WIDTH);
		createCombo(options);
		createBox();
	}
	
	private void createCombo(List<String> options){
		this.combo = new ComboBox<String>();
		this.combo.getItems().addAll(options);
		if(options.size() > 0){
			this.combo.setValue(options.get(0));
		}
	}
	
	private void createText(){
		text = new StringParameterInput("");
	}
	
	private void createBox(){
		this.getChildren().addAll(new Text(varName), combo);
	}
	
	public void appendTextInput(){
		this.getChildren().add(text);
		textAppended = true;
	}
	
	/**
	 * Removes the text input box (necessary if option needs input)
	 */
	public void removeTextInput(){
		if(textAppended){
			this.getChildren().remove(this.getChildren().size()-1);
			textAppended = false;
		}
	}
	
	/**
	 * Returns the combo box, helpful if want to set action on it
	 * @return combo box
	 */
	public ComboBox<String> getComboBox(){
		return combo;
	}

	@Override
	/**
	 * Returns value of the current combo box option
	 */
	public String getValue() {
		return combo.getValue();
	}
	
	/**
	 * Gets input from optional text input
	 * @return String
	 */
	public String getTextInput(){
		return text.getValue();
	}
	
	/**
	 * Gets value property of the combo, helpful for setting action on it
	 * @return ObjectProperty<String>
	 */
	public ObjectProperty<String> getValueProperty(){
		return this.combo.valueProperty();
	}
	
	@Override
	/**
	 * Get's type of the ParameterInput
	 */
	public String getType() {
		return TYPE;
	}

}
