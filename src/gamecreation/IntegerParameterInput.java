package gamecreation;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class IntegerParameterInput implements ParameterInput{
	public static final String TYPE = "Integer";
	private HBox complete;
	private String varName;
	private Slider slide;

	public IntegerParameterInput(String varName, int startRange, int endRange){
		createBox(startRange, endRange);
		this.varName = varName;
	}
	
	private void createBox(int startRange, int endRange){
		complete = new HBox();
		slide = new CustomSlider(startRange, endRange, startRange);
		slide.setBlockIncrement(1.0);
		complete.getChildren().addAll(new Text(varName), slide);
	}
	
	@Override
	public Node get() {
		return slide;
	}

	@Override
	public Integer getValue() {
		return (int) slide.getValue();
	}

	
}
