package gameDevelopmentInterface;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.VBox;

public class ParamVBox extends VBox {
	List<ConstructorNameAndEntry> myConstructorInfo;
	
	public ParamVBox() {
		myConstructorInfo = new ArrayList<ConstructorNameAndEntry>();
	}
	
	public void addConstructorInfo(ConstructorNameAndEntry cInfo) {
		myConstructorInfo.add(cInfo);
	}
	
	public List<ConstructorNameAndEntry> getConstructorInfo() {
		return myConstructorInfo;
	}

}
