package data;

import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 * 
 * @author Jake
 * Stores the data not specific to any screen, such as health, lives, score.
 */
public class GeneralModelData {
	/**
	 * Put in data representing variable-variable name pairs.
	 * @param data
	 */
	private List<Pair<String,String>> myData = new ArrayList<Pair<String,String>>();
	
	public void addData(Pair<String,String> data) {
		myData.add(data);
	}
}
