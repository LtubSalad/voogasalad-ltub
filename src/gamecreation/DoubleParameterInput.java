package gamecreation;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DoubleParameterInput implements ParameterInput{
	public static final String TYPE = "Double";
	private HBox complete;
	private String varName;
	private Slider slide;

	public DoubleParameterInput(String varName, double startRange, double endRange){
		this.varName = varName;
		createBox(startRange, endRange);
	}
	
	private void createBox(double startRange, double endRange){
		complete = new HBox(10);
		slide = new CustomSlider(startRange, endRange, startRange);
		Label value = new Label(Double.toString(startRange));
		slide.valueProperty().addListener(e -> value.setText(Double.toString(Math.floor(slide.getValue()* 100)/100)));
		
		Text title = new Text(varName);
		title.setWrappingWidth(100);
		complete.getChildren().addAll(title, slide, value);
	}
	
	@Override
	public Node get() {
		return complete;
	}

	@Override
	public Double getValue() {
		return slide.getValue();
	}

	@Override
	public String getType() {
		return TYPE;
	}

	
}
