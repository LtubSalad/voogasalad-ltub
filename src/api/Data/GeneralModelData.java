package api.Data;

import javafx.util.Pair;

/**
 * 
 * @author Daniel
 * Stores the data not specific to any screen, such as health, lives, score.
 */
public interface GeneralModelData {
	/**
	 * Put in data representing variable-variable name pairs.
	 * @param data
	 */
	public void addData(Pair<String,String> data);
}
