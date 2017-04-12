package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 * Serves as a way to store the data concerning how any given attribute is implemented, and pass it on.
 * An instance of AttributeData cannot store two subAttributes of the same kind. For example, the AttributeData
 * only possesses one instance of the ImageHolder attribute within its entire tree of attributes.
 * No repetition of function declarations/variable names allowed.
 * 
 * @author Daniel
 *
 */
public class AttributeData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String attributeType;
	private boolean changeableName;
	private String implementationSpecifier;
	private Map<Pair<String, List<String>>,String> attributeScripts;
	private Map<String,String> attributeVariables;
	private Map<String,List<String>> listVariables;
	private List<AttributeData> subAttributes;
	
	public AttributeData(String name, boolean isConcrete, String implementationSpecifier){
		attributeVariables=new HashMap<>();
		attributeScripts=new HashMap<>();
		listVariables=new HashMap<>();
		List<AttributeData> subAttributesUnobservable=new ArrayList<>();
		subAttributes=subAttributesUnobservable;
		attributeType=name;
	}
	
	public AttributeData(String name){
		this(name,false,null);
	}
	
	public Map<Pair<String, List<String>>,String> getScripts(){
		return attributeScripts;
	}
	
	public Map<String,String> getVariables(){
		return attributeVariables;
	}
	
	public void setScripts(Map<Pair<String, List<String>>,String> scripts){
		attributeScripts=scripts;
	}
	
	public void setVariables(Map<String, String> variables){
		attributeVariables=variables;
	}
	
	public void setName(String name){
		this.attributeType=name;
	}
	
	public String getName(){
		return attributeType;
	}

	public List<AttributeData> getAttributes(){
		return subAttributes;
	}
	
	public boolean nameModifiable(){
		return changeableName;
	}
	
	public void addAttributeData(AttributeData data){
		subAttributes.add(data);
	}
	
	public String getVariable(String variableName){
		if(attributeVariables.containsKey(variableName)){
			return attributeVariables.get(variableName);
		}
		for(AttributeData attribute:subAttributes){
			if(attribute.getVariable(variableName)!=null){
				return attribute.getVariable(variableName);
			}
		}
		return null;
	}
	
	public void setImplementation(String s){
		implementationSpecifier=s;
	}
	
	public String getImplementation(){
		return implementationSpecifier;
	}
	
	public boolean hasVariable(String variableName){
		if(attributeVariables.keySet().contains(variableName)){
			return true;
		}
		for(AttributeData attribute:subAttributes){
			if(attribute.hasVariable(variableName)){
				return true;
			}
		}
		return false;
	}
	
	public Map<String,List<String>> getListVariables(){
		return listVariables;
	}
	
	public void setVariable(String variableName, String variableValue){
		for(String name:attributeVariables.keySet()){
			if(name.equals(variableName)){
				attributeVariables.put(variableName, variableValue);
			}
		}
		for(AttributeData attribute:subAttributes){
			attribute.setVariable(variableName, variableValue);
		}
	}
	
	public void removeAttributeData(String attributeName){
		subAttributes.forEach((attributeData)->{
			if(attributeData.getName().equals(attributeName)){
				subAttributes.remove(attributeData);
			}
			attributeData.removeAttributeData(attributeName);
		});
	}

}