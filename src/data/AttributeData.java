package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
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
public class AttributeData {
	private String attributeName;
	private boolean changeableName;
	private boolean isConcrete;
	private String implementationSpecifier;
	private Map<Pair<String, List<String>>,String> attributeScripts;
	private Map<String,String> attributeVariables;
	private List<AttributeData> subAttributes;
	
	public AttributeData(String name, boolean changeableName, boolean isConcrete, String implementationSpecifier){
		attributeVariables=new HashMap<>();
		attributeScripts=new HashMap<>();
		List<AttributeData> subAttributesUnobservable=new ArrayList<>();
		subAttributes=subAttributesUnobservable;
		attributeName=name;
		this.isConcrete=isConcrete;
		this.changeableName=changeableName;
	}
	
	public AttributeData(String name){
		this(name, false, false,null);
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
		this.attributeName=name;
	}
	
	public String getName(){
		return attributeName;
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
	
	public void removeAttributeData(String attributeName){
		subAttributes.forEach((attributeData)->{
			if(attributeData.getName().equals(attributeName)){
				subAttributes.remove(attributeData);
			}
			attributeData.removeAttributeData(attributeName);
		});
	}

}
