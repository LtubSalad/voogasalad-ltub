package gameauthorgui.inputhelpers;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * This is a GUI component that takes in an integer
 * @author Matthew Tribby
 */
public class IntegerInputSlider extends HBox implements ParameterInput<Integer>{
	public static final String TYPE = "Integer";
	private String varName;
	private Slider slide;

	/**
	 * Creates a integer input with the given title and a certain range
	 * @param varName String name
	 * @param startRange start of range
	 * @param endRange end of range
	 */
	public IntegerInputSlider(String varName, int startRange, int endRange){
		super(10);
		this.varName = varName;
		createBox(startRange, endRange);
	}
	
	private void createBox(int startRange, int endRange){
		slide = new CustomSlider(startRange, endRange, startRange);
		Label value = new Label(Integer.toString(startRange));
		slide.valueProperty().addListener(e -> value.setText(Integer.toString((int) slide.getValue())));
		
		Text title = new Text(varName);
		title.setWrappingWidth(100);
		this.getChildren().addAll(title, slide, value);
	}
	

	@Override
	/**
	 * Returns the integer value of slide, truncated down
	 */
	public Integer getValue() {
		return (int) slide.getValue();
	}

	@Override
	/**
	 * Returns type of ParameterInput
	 */
	public String getType() {
		return TYPE;
	}


	
}
