package data;

import java.util.ArrayList;
import java.util.List;

import gameDevelopmentInterface.Path;
import gamecreation.level.LevelData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.util.Pair;
/**
 * 
 * @author Jake, Daniel
 * Stores the data not specific to any screen, such as health, lives, score.
 */
public class DeveloperData {
	private static final String NUMBER_OF_LIVES = "Number of Lives";
	private static final String NUMBER_OF_LEVELS = "Number of Levels";
	private static final String NUMBER_OF_STARTING_GOLD = "Number of Starting Gold";
	private static final String NUMBER_OF_STARTING_BONUSES = "Number of Starting Bonuses";
	private ObservableMap<String,String> myData = FXCollections.observableHashMap();
	private ObservableList<LevelData> levelData; // need 
	private ObservableList<Path> myPaths;
	private ObservableList<SpriteMakerModel> mySprites; // need 
	private SpritesForScreenUse jakeSprites; // need
	
	public DeveloperData() {
		List<SpriteMakerModel> list=new ArrayList<SpriteMakerModel>();
		levelData = FXCollections.observableArrayList();
		mySprites=FXCollections.observableList(list);
		jakeSprites=new SpritesForScreenUse();
		List<Path> dummyPaths=new ArrayList<>();
		myPaths=FXCollections.observableList(dummyPaths);
		myPaths.add(new Path());
		myData.put(NUMBER_OF_STARTING_BONUSES, "");
		myData.put(NUMBER_OF_STARTING_GOLD, "");	
		myData.put(NUMBER_OF_LEVELS, "");
		myData.put(NUMBER_OF_LIVES, "");
	}
	
	public void addSprite(SpriteMakerModel sprite){
		//FIXME jake sprite?
		jakeSprites.addPossibleSprite(sprite);
		System.out.println("reached end");
	}
	
	public void addPath(Path path){
		myPaths.add(path);
	}
	
	public ObservableList<Path> getPaths(){
		return myPaths;
	}
	
	public ObservableList<SpriteMakerModel> getSprites(){
		return mySprites;
	}
	
	public SpritesForScreenUse getScreenSprites(){
		return jakeSprites;
	}
	
	public ObservableList<LevelData> getLevelData(){
		return levelData;
	}
	
	/**
	 * Put in data representing variable-variable name pairs.
	 * @param data
	 */
	public void addData(Pair<String,String> data) {
		myData.put(data.getKey(), data.getValue());
	}
	/**
	 * 
	 * @return all of the data held in this model
	 */
	public ObservableMap<String,String> getAllData() {
		return myData;
	}
}
