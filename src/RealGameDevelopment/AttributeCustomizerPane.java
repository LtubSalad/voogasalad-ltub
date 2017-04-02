package RealGameDevelopment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AttributeCustomizerPane extends BorderPane{
	//The data structures listed below should ideally be obtained from the Attribute somehow
	private Map<String, String> imageParameters;
	private Map<String, String> simpleParameters;
	private Map<String, List<String>> functions;
	
	public AttributeCustomizerPane(){
		simpleParameters=new HashMap<>();
		simpleParameters.put("attribute name", "foo");
		simpleParameters.put("health", "foo");
		simpleParameters.put("parameter 3", "foo");
		
		functions=new HashMap<>();
		functions.put("run", new ArrayList<String>());
		ArrayList<String> test=new ArrayList<String>();
		test.add("parameter1name");
		test.add("parameter2name");
		functions.put("MultiInputFunction", test);
		
		this.setTop(simpleParameterSelector());
		this.setBottom(scriptFunctionInterface());
	}
	
	private VBox simpleParameterSelector(){
		VBox parameterSelector=new VBox();
		simpleParameters.forEach((parameterName, parameter)-> {
			Label whatParameter=new Label(parameterName);
			TextField parameterSetter= new TextField("Input parameter");
			parameterSetter.setPrefWidth(100);
			whatParameter.setPrefWidth(300);
			HBox selectorRow=new HBox();
			selectorRow.getChildren().addAll(whatParameter,parameterSetter);
			parameterSelector.getChildren().add(selectorRow);
		});
		
		return parameterSelector;
	}
	
	private VBox scriptFunctionInterface(){
		VBox scriptingInterface = new VBox();
		functions.forEach((functionName, parameters)->{
			VBox singleFunctionEditor= new VBox();
			String functionDeclaration= "Function: "+ functionName;
			if(!parameters.isEmpty()){
				functionDeclaration+="|    Parameters:";
				for(String parameter:parameters){
					functionDeclaration += " ";
					functionDeclaration += parameter;
				};
			}
			Label functionInfo= new Label(functionDeclaration);
			TextArea scriptBox= new TextArea("Write scripts here");
			singleFunctionEditor.getChildren().addAll(functionInfo,scriptBox);
			scriptingInterface.getChildren().add(singleFunctionEditor);
		});
		
		return scriptingInterface;
	}
}
