package api.Data;

import java.util.List;
import java.util.Map;

import javafx.util.Pair;

//Serves as a way to store the data concerning how any given attribute is implemented, and pass it on.
public class AttributeData {
	//The first string represents the function name and its inputs, and the second represents the actual
	//script it uses.
	private String attributeName;
	private Map<Pair<String, List<String>>,String> attributeScripts;
	private Map<String,String> attributeVariables;
	
	public AttributeData(String name, Map<String,String> variables, Map<Pair<String,List<String>>,String> scripts){
		attributeName=name;
		attributeVariables=variables;
		attributeScripts=scripts;
	}
	
	public String getName(){
		return attributeName;
	}
}
