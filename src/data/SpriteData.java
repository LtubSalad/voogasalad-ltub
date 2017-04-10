package data;

import java.util.ArrayList;
import java.util.List;

public class SpriteData{
	private List<AttributeData> subAttributes;
	private String name;
	
	public SpriteData(String name) {
		this.name=name;
		subAttributes=new ArrayList<>();
	}
	
	public List<AttributeData> getAttributes(){
		return subAttributes;
	}

}
