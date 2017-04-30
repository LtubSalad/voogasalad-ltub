package gameauthorgui.inputhelpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.stage.Stage;

public class DataWrapperTester extends Application{

	public static void main(String[] args) {
		
		Map<String, DataWrapper> stuff = new HashMap<String, DataWrapper>();
		stuff.put("Integer", new GenericDataWrapper<Integer>(7));
		
		List<DataWrapper> data2 = new ArrayList<DataWrapper>();
		GenericDataWrapper<Integer> tester = new GenericDataWrapper<Integer>(5);
		data2.add(tester);
		
		List<Class<?>> classes = new ArrayList<Class<?>>();
		classes.add(Integer.class);
		
		if(classes.contains(data2.get(0).checkType())){
			System.out.println("correct type");
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}

}
