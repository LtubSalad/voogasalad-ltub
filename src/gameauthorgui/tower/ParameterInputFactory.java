package gameauthorgui.tower;

import java.util.HashMap;
import java.util.Map;



import gamecreation.DoubleParameterInput;
import gamecreation.ImageParameterInput;
import gamecreation.IntegerInputSlider;
import gamecreation.ParameterInput;
import gamecreation.StringParameterInput;
import javafx.scene.image.Image;

public class ParameterInputFactory {
	Map<Class<?>, Class<? extends ParameterInput<?>>> parameterInputs;
	
	public ParameterInputFactory(){
		initMap();
	}
	
	private void initMap(){
		parameterInputs = new HashMap<>();
		parameterInputs.put(String.class, StringParameterInput.class);
		parameterInputs.put(Integer.class, IntegerInputSlider.class);
		parameterInputs.put(Double.class, DoubleParameterInput.class);
		parameterInputs.put(Image.class, ImageParameterInput.class);
	}
	
	public ParameterInput<?> getInput(Class<?> clazz) {
		if(parameterInputs.containsKey(clazz)){
			try {
				return parameterInputs.get(clazz).newInstance();
			}catch(Exception e){
				//FIXME
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
