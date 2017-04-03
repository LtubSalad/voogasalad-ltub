package data;

import java.util.ArrayList;
import java.util.List;

public class ClassData {
	private String className;
	private List<AttributeData> classAttributes;
	
	public ClassData(String name){
		classAttributes=new ArrayList<AttributeData>();
		className=name;
	}
	
	public void setName(String name){
		className=name;
	}
	
	public void addAttributeData(AttributeData data){
		removeAttributeData(data.getName());
		classAttributes.add(data);
	}
	
	public void removeAttributeData(String attributeName){
		classAttributes.forEach((attributeData)->{
			if(attributeData.getName().equals(attributeName)){
				classAttributes.remove(attributeData);
			}
		});
	}
}
