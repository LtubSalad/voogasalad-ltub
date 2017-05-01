package gameauthorgui.inputhelpers;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ComboBoxParameterInput extends HBox implements ParameterInput<String>{
	public static final String TYPE = "String";
	public static final int TEXT_INPUT_MAX_WIDTH = 100;
	private String varName;
	private ComboBox<String> combo;
	private ButtonStringParameterInput text;
	private boolean textAppended = false;
	
	public ComboBoxParameterInput(String varName, List<String> options){
		super();
		this.varName = varName;
		createText();
		text.setMaxWidth(TEXT_INPUT_MAX_WIDTH);
		createCombo(options);
		createBox();
		appendTextInput();
	}
	
	private void createCombo(List<String> options){
		this.combo = new ComboBox<String>();
		this.combo.getItems().addAll(options);
		if(options.size() > 0){
			this.combo.setValue(options.get(0));
		}
	}
	
	private void createText(){
		text = new ButtonStringParameterInput("");
	}
	
	private void createBox(){
		this.getChildren().addAll(new Text(varName), combo);
	}
	
	public void appendTextInput(){
		this.getChildren().add(text);
		textAppended = true;
	}
	
	public void removeTextInput(){
		if(textAppended){
			this.getChildren().remove(this.getChildren().size()-1);
			textAppended = false;
		}
	}

	@Override
	public String getValue() {
		return combo.getValue();
	}
	
	public String getTextInput(){
		return text.getText();
	}
	
	public Button getDoneButton(){
		return text.getDoneButton();
	}
	
	public ObjectProperty<String> getValueProperty(){
		return this.combo.valueProperty();
	}
	
	@Override
	public String getType() {
		return TYPE;
	}

}
