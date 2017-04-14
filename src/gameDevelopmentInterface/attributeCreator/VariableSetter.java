package gameDevelopmentInterface.attributeCreator;

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
 */
public class VariableSetter extends VBox{
	private final List<SingleVariableSetter> singleVariableSetters;
	private boolean customizableVariables=false;
	
	public VariableSetter(Map<String,String> variables, boolean customizableVariables){
		this.customizableVariables=customizableVariables;
		singleVariableSetters= new ArrayList<>();
		this.getChildren().add(new Label("Variables"));
		variables.forEach((parameterName, value) -> {
			SingleVariableSetter singleVariableSetter;
			if(this.customizableVariables){
				singleVariableSetter=new UnrestrictedSingleVariableSetter(parameterName,value);
			}else{
				singleVariableSetter=new RestrictedSingleVariableSetter(parameterName,value);
			}
			this.getChildren().add(singleVariableSetter);
			singleVariableSetters.add(singleVariableSetter);
		});
	}
	
	public VariableSetter(Map<String,String> variables){
		this(variables,true);
	}
	
	
	abstract class SingleVariableSetter extends HBox{
		public SingleVariableSetter(String variableName, String value) {
		}
		
		protected abstract Pair<String,String> getVariableDetails();
		
	}
	
	public Map<String, String> getVariables(){
		Map<String,String> variablesWithValues= new HashMap<>();
		singleVariableSetters.forEach((variableSetter)->{
			Pair<String,String> variableDetails=variableSetter.getVariableDetails();
			variablesWithValues.put(variableDetails.getKey(),variableDetails.getValue());
		});
		return variablesWithValues;
	}
	
	class UnrestrictedSingleVariableSetter extends SingleVariableSetter{
		private final TextField variableField;
		private final TextField variableValue;
		private UnrestrictedSingleVariableSetter(String variableName, String value){
			super(variableName,value);
			this.variableField=new TextField(variableName);
			variableValue = new TextField(value);
			variableField.setPrefWidth(200);
			variableValue.setPrefWidth(300);
			this.getChildren().addAll(variableField, variableValue);		
		}
		
		public Pair<String,String> getVariableDetails(){
			return new Pair(variableField.getText(),variableValue.getText());
		}
	}
	
	class RestrictedSingleVariableSetter extends SingleVariableSetter{
		private final String variableName;
		private final TextField variableValue;
		private RestrictedSingleVariableSetter(String variableName, String value){
			super(variableName,value);
			this.variableName=variableName;
			Label nameDisplay = new Label(variableName);
			variableValue = new TextField(value);
			nameDisplay.setPrefWidth(100);
			variableValue.setPrefWidth(300);
			this.getChildren().addAll(nameDisplay, variableValue);			
		}
		
		public Pair<String,String> getVariableDetails(){
			return new Pair(variableName,variableValue.getText());
		}
	}
}
