package gameDevelopmentInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class FunctionSetter extends VBox {
	List<SingleFunctionSetter> singleFunctionSetters;

	public FunctionSetter(Map<Pair<String, List<String>>, String> functions) {
		VBox scriptingInterface = new VBox();
		singleFunctionSetters = new ArrayList<>();
		this.getChildren().add(new Label("Functions"));
		functions.forEach((declarationDetails, script) -> {
			SingleFunctionSetter functionSetter = new SingleFunctionSetter(declarationDetails.getKey(),
					declarationDetails.getValue(), script);
			this.getChildren().add(functionSetter);
			singleFunctionSetters.add(functionSetter);
		});
	}

	class SingleFunctionSetter extends VBox {
		private String functionName;
		private List<String> parameters;
		private TextArea scriptBox;

		private SingleFunctionSetter(String functionName, List<String> parameters, String script) {
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

		private String getName() {
			return functionName;
		}

		private List<String> getParameters() {
			return parameters;
		}

		private String getScript() {
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
