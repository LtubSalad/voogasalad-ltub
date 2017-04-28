package gameauthorgui.inputhelpers;

import java.util.List;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ComboBoxParameterInput extends HBox implements ParameterInput<String>{
	public static final String TYPE = "String";
	private String varName;
	private ComboBox<String> combo;
	
	public ComboBoxParameterInput(String varName, List<String> options){
		super();
		this.varName = varName;
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
	
	private void createBox(){
		this.getChildren().addAll(new Text(varName), combo);
	}

	@Override
	public String getValue() {
		return combo.getValue();
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
