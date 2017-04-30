package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import gamecreation.level.ILevelData;
import gamecreation.level.LevelData;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 * @author tahiaemran
 *
 *serializable version of developer data for saving authored game files 
 *
 */
public class SerializableDeveloperData {
	private Map<String, String> gameData; 
	private List<SpriteMakerModel> mySprites; 
	private List<ILevelData> myLevels; 
	private SpritesForScreenUse screenSprites; 
	private String gameName; 
	private String gameIconFile; 
	
	//DeveloperData dataToTranslate; // TODO: see if this serializes ok 
	
	public SerializableDeveloperData(DeveloperData data){
		this.screenSprites = data.getScreenSprites();
		configData(data.getAllData()); 
		configSprites(data.getSprites()); 
		myLevels = data.getReadOnlyLevelData();
		
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
	
	public SpritesForScreenUse getScreenSprites(){
		return this.screenSprites;
	}
	

	
}
