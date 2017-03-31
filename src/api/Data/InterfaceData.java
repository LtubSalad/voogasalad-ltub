package api.Data;

import java.util.Map;

//Serves as a way to store the data concerning how any given interface is implemented, and pass it on.
public interface InterfaceData {
	//The first string represents the function name and its inputs, and the second represents the actual
	//script it uses.
	public Map<String,String> getFunctionData();
}
