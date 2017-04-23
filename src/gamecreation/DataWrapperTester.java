package gamecreation;

import java.util.HashMap;
import java.util.Map;

public class DataWrapperTester {

	public static void main(String[] args) {
		// component 	variables
		//String, Map<String, DataWrapper>
		
		//Suppressed warnings because the goal of this map is to take in all different types
		Map<String, DataWrapper> data = new HashMap<>();
		
		data.put("Speed", new GenericDataWrapper<Integer>(5));
		
		System.out.println(data.get("Speed").get());
	}

}
