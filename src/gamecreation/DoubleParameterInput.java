package gamecreation;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DoubleParameterInput implements ParameterInput{
	public static final String TYPE = "Double";
	private HBox complete;
	private String varName;
	private Slider slide;

	public DoubleParameterInput(String varName, double startRange, double endRange){
		createBox(startRange, endRange);
		this.varName = varName;
	}
	
	private void createBox(double startRange, double endRange){
		complete = new HBox();
		slide = new CustomSlider(startRange, endRange, startRange);
		complete.getChildren().addAll(new Text(varName), slide);
	}
	
	@Override
	public Node get() {
		return slide;
	}

	@Override
	public Double getValue() {
		return slide.getValue();
	}

	
}
