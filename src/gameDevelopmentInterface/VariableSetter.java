package gameDevelopmentInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
/**
 * Provides a user interface allowing the developer to adjust a list of preprovided variables and save the modified 
 * contents.
 * @author Daniel
 *
 */
public class VariableSetter extends VBox{
	private final List<SingleVariableSetter> singleVariableSetters;
	
	public VariableSetter(Map<String,String> variables){
		singleVariableSetters= new ArrayList<>();
		this.getChildren().add(new Label("Variables"));
		variables.forEach((parameterName, value) -> {
			SingleVariableSetter singleVariableSetter=new SingleVariableSetter(parameterName,value);
			this.getChildren().add(singleVariableSetter);
			singleVariableSetters.add(singleVariableSetter);
		});
	}
	
	class SingleVariableSetter extends HBox{
		private final String variableName;
		private final TextField variableValue;
		private SingleVariableSetter(String variableName, String value){
			this.variableName=variableName;
			Label nameDisplay = new Label(variableName);
			variableValue = new TextField(value);
			nameDisplay.setPrefWidth(100);
			variableValue.setPrefWidth(300);
			this.getChildren().addAll(nameDisplay, variableValue);
			
		}
		
		private Pair<String,String> getVariableDetails(){
			return new Pair(variableName,variableValue.getText());
		}
	}
	
	public Map<String, String> getVariables(){
		Map<String,String> variablesWithValues= new HashMap<>();
		singleVariableSetters.forEach((variableSetter)->{
			Pair<String,String> variableDetails=variableSetter.getVariableDetails();
			variablesWithValues.put(variableDetails.getKey(),variableDetails.getValue());
		});
		return variablesWithValues;
	}
	

}
