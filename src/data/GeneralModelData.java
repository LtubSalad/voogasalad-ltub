package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.util.Pair;
/**
 * 
 * @author Jake
 * Stores the data not specific to any screen, such as health, lives, score.
 */
public class GeneralModelData {
	private static final String NUMBER_OF_LIVES = "Number of Lives";
	private static final String NUMBER_OF_LEVELS = "Number of Levels";
	private static final String NUMBER_OF_STARTING_GOLD = "Number of Starting Gold";
	private static final String NUMBER_OF_STARTING_BONUSES = "Number of Starting Bonuses";
	private ObservableMap<String,String> myData = FXCollections.observableHashMap();
	
	public GeneralModelData() {
		myData.put(NUMBER_OF_STARTING_BONUSES, "");
		myData.put(NUMBER_OF_STARTING_GOLD, "");
		myData.put(NUMBER_OF_LEVELS, "");
		myData.put(NUMBER_OF_LIVES, "");
	}
	/**
	 * Put in data representing variable-variable name pairs.
	 * @param data
	 */
	public void addData(Pair<String,String> data) {
		myData.put(data.getKey(), data.getValue());
	}
	
	public ObservableMap<String,String> getAllData() {
		return myData;
	}
}
