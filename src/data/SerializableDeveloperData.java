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
		//this.dataToTranslate = data; 
		this.screenSprites = data.getScreenSprites();
		configData(data); 
		configSprites(data); 
		myLevels = data.getReadOnlyLevelData();
		
	}

	private void configSprites(DeveloperData dataToTranslate) {
		ObservableList<SpriteMakerModel> spriteData = dataToTranslate.getSprites();
		mySprites = new ArrayList<SpriteMakerModel>();
		mySprites = spriteData.stream().collect(Collectors.toList());
	}

	private void configData(DeveloperData dataToTranslate) {
		gameData = new HashMap<String, String>();	
		for (String dataName: dataToTranslate.getAllData().keySet()){
			gameData.put(dataName, dataToTranslate.getAllData().get(dataName));
		}
		
		gameData.put("GAME_NAME", this.gameName);
		gameData.put("GAME_FILE", this.gameIconFile);
	}
	
	public List<SpriteMakerModel> getSprites(){
		return Collections.unmodifiableList(mySprites); 
	}
	
	public List<ILevelData> getLevelData(){
		return Collections.unmodifiableList(myLevels);
	}
	
	public Map<String, String> getGameData(){
		return Collections.unmodifiableMap(gameData);
	}
	
	public SpritesForScreenUse getScreenSprites(){
		return this.screenSprites;
	}
	

	
}
