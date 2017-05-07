package gameauthorgui.inputhelpers;

import javafx.scene.control.Slider;

/**
 * Creates a custom slider with a bunch of presets
 * @author Matthew Tribby
 */
public class CustomSlider extends Slider{

	/**
	 * Creates a CustomSlider with the starting value, ending value, and a current value and then 
	 * sets the basics characteristics
	 * @param startingVal
	 * @param endingVal
	 * @param currentVal
	 */
	public CustomSlider(double startingVal, double endingVal, double currentVal){
		super();
		this.setMin(startingVal);
		this.setValue(currentVal);
		this.setMax(endingVal);
		this.setShowTickLabels(true);
		this.setShowTickMarks(true);
	}
}
