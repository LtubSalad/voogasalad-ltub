package gameDevelopmentInterface;

import java.util.List;
import java.util.Map;


import data.AttributeData;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class AttributeCustomizerPane extends VBox {
	private AttributeData myAttribute;
	
	public AttributeCustomizerPane(AttributeData attributeData){
		myAttribute=attributeData;
		this.getChildren().addAll(new Label("Attribute Name: "+myAttribute.getName()),
				variableSetter(attributeData.getVariables()),functionSetter(attributeData.getScripts()));
		myAttribute.getAttributes().forEach((subAttribute)->{
			this.getChildren().add(new AttributeCustomizerPane(subAttribute));
		});	
	}

	private VBox variableSetter(Map<String, String> variables) {
		VBox parameterSelector = new VBox();
		variables.forEach((parameterName, parameter) -> {
			Label whatParameter = new Label(parameterName);
			TextField parameterSetter = new TextField(parameter);
			parameterSetter.setPrefWidth(100);
			whatParameter.setPrefWidth(300);
			HBox selectorRow = new HBox();
			selectorRow.getChildren().addAll(whatParameter, parameterSetter);
			parameterSelector.getChildren().add(selectorRow);
		});

		return parameterSelector;
	}

	private VBox functionSetter(Map<Pair<String,List<String>>,String> functions) {
		VBox scriptingInterface = new VBox();
		functions.forEach((declarationDetails, script) -> {
			VBox singleFunctionEditor = new VBox();
			String functionDeclaration = "Function: " + declarationDetails.getKey();
			if (!declarationDetails.getValue().isEmpty()) {
				functionDeclaration += "|    Parameters:";
				for (String parameter : declarationDetails.getValue()) {
					functionDeclaration += " ";
					functionDeclaration += parameter;
				}
			}
			Label functionInfo = new Label(functionDeclaration);
			TextArea scriptBox = new TextArea(script);
			singleFunctionEditor.getChildren().addAll(functionInfo, scriptBox);
			scriptingInterface.getChildren().add(singleFunctionEditor);
		});

		return scriptingInterface;
	}
	
	public AttributeData getAttribute(){
		return myAttribute;
	}
	
}
