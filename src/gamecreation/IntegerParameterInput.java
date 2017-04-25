package gamecreation;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class IntegerParameterInput implements ParameterInput{
	public static final String TYPE = "Integer";
	private HBox complete;
	private String varName;
	private Slider slide;

	public IntegerParameterInput(String varName, int startRange, int endRange){
		this.varName = varName;
		createBox(startRange, endRange);
	}
	
	private void createBox(int startRange, int endRange){
		complete = new HBox(10);
		slide = new CustomSlider(startRange, endRange, startRange);
		Label value = new Label(Integer.toString(startRange));
		slide.valueProperty().addListener(e -> value.setText(Integer.toString((int) slide.getValue())));
		
		Text title = new Text(varName);
		title.setWrappingWidth(100);
		complete.getChildren().addAll(title, slide, value);
	}
	
	@Override
	public HBox get() {
		return complete;
	}

	@Override
	public Integer getValue() {
		return (int) slide.getValue();
	}

	@Override
	public String getType() {
		return TYPE;
	}

	
}
