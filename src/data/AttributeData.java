package data;

import java.util.ArrayList;
import java.util.HashMap;
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
	private List<AttributeData> subAttributes;
	
	public AttributeData(String name, Map<String,String> variables, Map<Pair<String,List<String>>,String> scripts){
		attributeName=name;
		attributeVariables=variables;
		attributeScripts=scripts;
	}
	
	public AttributeData(String name){
		attributeVariables=new HashMap<>();
		attributeScripts=new HashMap<>();
		subAttributes=new ArrayList<>();
		attributeName=name;
		
	}
	
	public AttributeData(List<AttributeData> subAttributes){
		this.subAttributes=subAttributes;
		attributeVariables=new HashMap<>();
		attributeScripts= new HashMap<>();
	}
	
	public Map<Pair<String, List<String>>,String> getScripts(){
		return attributeScripts;
	}
	
	public Map<String,String> getVariables(){
		return attributeVariables;
	}
	
	public String getName(){
		return attributeName;
	}
	/*
	public AttributeData getAttribute(String attributeName){
		if(this.attributeName.equals(attributeName)){
			return this;
		}
		for(AttributeData attributeData:subAttributes){
			if(attributeData.getAttribute(attributeName)!=null){
				return attributeData.getAttribute(attributeName);
			}
		};
		return null;
	}*/

	public List<AttributeData> getAttributes(){
		return subAttributes;
	}
	
	public void addAttributeData(AttributeData data){
		removeAttributeData(data.getName());
		subAttributes.add(data);
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
