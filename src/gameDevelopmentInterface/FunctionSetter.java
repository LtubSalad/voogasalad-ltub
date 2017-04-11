package gameDevelopmentInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class FunctionSetter extends VBox{
	List<SingleFunctionSetter> singleFunctionSetters;
	private boolean modifiableFunctions;

	public FunctionSetter(Map<Pair<String, List<String>>, String> functions, boolean modifiableFunctions) {
		this.modifiableFunctions=modifiableFunctions;
		VBox scriptingInterface = new VBox();
		singleFunctionSetters = new ArrayList<>();
		this.getChildren().add(new Label("Functions"));
		functions.forEach((declarationDetails, script) -> {
			SingleFunctionSetter singleSetter;
			if(modifiableFunctions){
				singleSetter=new UnrestrictedSingleSetter(declarationDetails.getKey(),declarationDetails.getValue(),script);
			}
			else{
			singleSetter = new RestrictedFunctionSetter(declarationDetails.getKey(),
					declarationDetails.getValue(), script);
			}
			this.getChildren().add(singleSetter);
			singleFunctionSetters.add(singleSetter);
		});
	}
	
	public FunctionSetter(Map<Pair<String, List<String>>, String> functions) {
		this(functions,false);
	}
	
	abstract class SingleFunctionSetter extends VBox{
		public SingleFunctionSetter(String functionName, List<String> parameters, String script){
			
		}
		
		public abstract String getName();

		public abstract List<String> getParameters();

		public abstract String getScript();
	}
	
	class UnrestrictedSingleSetter extends SingleFunctionSetter{
		private FieldSetter nameSetter;
		private VBox parameterDisplay;
		private TextArea scriptBox;

		public UnrestrictedSingleSetter(String functionName, List<String> parameters, String script) {
			super(functionName, parameters, script);
			nameSetter=new FieldSetter(functionName,true);
			parameterDisplay=new VBox();
			parameters.forEach((parameter)->{
				TextField field=new TextField(parameter);
				parameterDisplay.getChildren().add(field);
			});
			scriptBox=new TextArea(script);
			Button addParameter=new Button("Add parameter");
			addParameter.setOnAction((click)->{
				//parameterDisplay.getChildren().add(new TextField("dummy"));
			});
			this.getChildren().addAll(nameSetter,parameterDisplay,scriptBox, addParameter);
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return nameSetter.getString();
		}

		@Override
		public List<String> getParameters() {
			// TODO Auto-generated method stub
			return new ArrayList<>();
		}

		@Override
		public String getScript() {
			// TODO Auto-generated method stub
			return scriptBox.getText();
		}
		
		
	}
	

	class RestrictedFunctionSetter extends SingleFunctionSetter {
		private String functionName;
		private List<String> parameters;
		private TextArea scriptBox;

		private RestrictedFunctionSetter(String functionName, List<String> parameters, String script) {
			super(functionName,parameters,script);
			String functionDeclaration = "Function: " + functionName;
			if (!parameters.isEmpty()) {
				functionDeclaration += "|    Parameters:";
				for (String parameter : parameters) {
					functionDeclaration += " ";
					functionDeclaration += parameter;
				}
			}
			Label functionInfo = new Label(functionDeclaration);
			this.scriptBox = new TextArea(script);
			this.functionName = functionName;
			this.parameters = parameters;
			this.getChildren().addAll(functionInfo, scriptBox);
		}

		public String getName() {
			return functionName;
		}

		public List<String> getParameters() {
			return parameters;
		}

		public String getScript() {
			return scriptBox.getText();
		}

	}

	public Map<Pair<String, List<String>>, String> getFunctions() {
		Map<Pair<String, List<String>>, String> functions = new HashMap<>();
		singleFunctionSetters.forEach((functionSetter) -> {
			Pair<String, List<String>> functionDeclaration = new Pair<>(functionSetter.getName(),
					functionSetter.getParameters());
			String script = functionSetter.getScript();
			functions.put(functionDeclaration, script);
		});
		return functions;
	}

}
