package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import gamecreation.level.ILevelData;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import newengine.managers.conditions.Condition;
import newengine.player.Player;

/**
 * @author tahiaemran
 *
 *serializable version of developer data for saving authored game files 
 *
 */
public class SerializableDeveloperData {
	public static final String NUMBER_OF_LIVES = "NUM_LIVES";
	public static final String BUILD_TOWER = "BUILD_IN_GAME";
	public static final String NUMBER_OF_STARTING_GOLD = "NUM_GOLD";
	public static final String LEVEL_COMPLETION_BONUS = "LEVEL_COMPLETION";
	public static final String GAME_NAME = "GAME_NAME";
	public static final String GAME_ICON = "GAME_ICON";
	private Map<String, String> gameData; 
	private List<SpriteMakerModel> mySprites; 
	private List<ILevelData> myLevels; 
	
	public SerializableDeveloperData(DeveloperData data){
		configData(data.getAllData()); 
		configSprites(data.getSprites()); 
		myLevels = data.getReadOnlyLevelData();
		
	}

	public Player getUserPlayer() {
		//TODO
		return new Player("TOWERS");
	}

	private void configSprites(ObservableList<SpriteMakerModel> data) {
		mySprites = new ArrayList<SpriteMakerModel>();
		mySprites = data.stream().collect(Collectors.toList());
	}


	private void configData(ObservableMap<String, String> dataToTranslate) {
		gameData = new HashMap<String, String>();	
		for (String dataName: dataToTranslate.keySet()){
			gameData.put(dataName, dataToTranslate.get(dataName));
		}
	}
	
	public List<SpriteMakerModel> getSprites(){
		return Collections.unmodifiableList(mySprites); 
	}
	
	public List<ILevelData> getLevels(){
		return Collections.unmodifiableList(myLevels);
	}
	
	public Map<String, String> getGameData(){
		return Collections.unmodifiableMap(gameData);
	}
	
	
}
