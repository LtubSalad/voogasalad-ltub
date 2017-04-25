package gamecreation;

import javafx.scene.control.Slider;

public class CustomSlider extends Slider{

	public CustomSlider(double startingVal, double endingVal, double currentVal){
		super();
		this.setMin(startingVal);
		this.setValue(currentVal);
		this.setMax(endingVal);
		this.setShowTickLabels(true);
		this.setShowTickMarks(true);
	}
}
