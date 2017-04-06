package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

/**
 * Serves as a way to store the data concerning how any given attribute is implemented, and pass it on.
 * An instance of AttributeData cannot store two subAttributes of the same kind. For example, the AttributeData
 * only possesses one instance of the ImageHolder attribute within its tree of attributes.
 * @author Daniel
 *
 */
public class AttributeData {
	//The first string represents the function name and its inputs, and the second represents the actual
	//script it uses.
	private String attributeName;
	private Map<Pair<String, List<String>>,String> attributeScripts;
	private Map<String,String> attributeVariables;
	private List<AttributeData> subAttributes;
	
	public AttributeData(String name){
		attributeVariables=new HashMap<>();
		attributeScripts=new HashMap<>();
		subAttributes=new ArrayList<>();
		attributeName=name;
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
