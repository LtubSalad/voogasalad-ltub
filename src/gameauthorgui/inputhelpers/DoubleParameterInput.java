package gameauthorgui.inputhelpers;

import java.text.DecimalFormat;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DoubleParameterInput extends HBox implements ParameterInput<Double>{
	public static final String TYPE = "Double";
	private String varName;
	private Slider slide;

	public DoubleParameterInput(String varName, double startRange, double endRange){
		super(10);
		this.varName = varName;
		createBox(startRange, endRange);
	}
	
	private void createBox(double startRange, double endRange){
		slide = new CustomSlider(startRange, endRange, startRange);
		Label value = new Label(Double.toString(startRange));
		
		//http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
		DecimalFormat df = new DecimalFormat("#.00");
		slide.valueProperty().addListener(e -> value.setText(df.format(slide.getValue())));
		
		Text title = new Text(varName);
		title.setWrappingWidth(100);
		this.getChildren().addAll(title, slide, value);
	}
	
	@Override
	public Double getValue() {
		return slide.getValue();
	}
	
	/**
	 * Used to listen to the changes of the parameter input
	 * @return DoubleProperty
	 */
	public DoubleProperty getDoubleProperty(){
		return slide.valueProperty();
	}

	@Override
	public String getType() {
		return TYPE;
	}

	
}
