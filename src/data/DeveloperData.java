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
 * Stores the information needed to specify how an entire game should be run, though it needs to be converted
 * to serializable version before xstream.
 * Also tracks info such as available paths and sprites that can be added to a screen for developer purposes. 
 */
public class DeveloperData {
	private static final String NUMBER_OF_LIVES = "NUM_LIVES";
	private static final String BUILD_TOWER = "BUILD_IN_GAME";
	private static final String NUMBER_OF_STARTING_GOLD = "NUM_GOLD";
	private static final String LEVEL_COMPLETION_BONUS = "LEVEL_COMPLETION";
	private static final String GAME_NAME = "GAME_NAME";
	private static final String GAME_ICON = "GAME_ICON";
	private ObservableMap<String,String> myData = FXCollections.observableHashMap();
	private ObservableList<LevelData> levelData;
	private ObservableList<Path> myPaths;
	private ObservableList<SpriteMakerModel> mySprites; //need
	private SpritesForScreenUse jakeSprites = new SpritesForScreenUse();  //need
	private String gameName; //need
	private String gameIconFilePath;//need
 	
	public DeveloperData() {
		List<SpriteMakerModel> list=new ArrayList<SpriteMakerModel>();
		levelData = FXCollections.observableArrayList();
		mySprites=FXCollections.observableList(list);
		jakeSprites=new SpritesForScreenUse();
		List<Path> dummyPaths=new ArrayList<>();
		myPaths=FXCollections.observableList(dummyPaths);
		myPaths.add(new Path());
		myData.put(LEVEL_COMPLETION_BONUS, "");
		myData.put(NUMBER_OF_STARTING_GOLD, "");	
		myData.put(BUILD_TOWER, "false");
		myData.put(NUMBER_OF_LIVES, "");
		myData.put(GAME_NAME, "");
		myData.put(GAME_ICON, "");
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
//	
//		if(myData.containsKey(NUMBER_OF_LIVES))
//		System.out.println("Number of Lives :" + myData.get(NUMBER_OF_LIVES));
//		if(myData.containsKey(BUILD_TOWER))
//		System.out.println("Build tower: " + myData.get(BUILD_TOWER));
//		if(myData.containsKey(NUMBER_OF_STARTING_GOLD))
//		System.out.println("Number of Starting Gold: " + myData.get(NUMBER_OF_STARTING_GOLD));
//		if(myData.containsKey(LEVEL_COMPLETION_BONUS))
//		System.out.println("Level completion bonus: " + myData.get(LEVEL_COMPLETION_BONUS));
//		if(myData.containsKey(GAME_NAME))
//		System.out.println("Game name: " + myData.get(GAME_NAME));
//		if(myData.containsKey(GAME_ICON))
//		System.out.println("Game icon: " + myData.get(GAME_ICON));
//		
	}
	
	/**
	 * @return all of the data held in this model
	 */
	public ObservableMap<String,String> getAllData() {
		return myData;
	}
	
	public void setGameName(String name){
		this.gameName = name;
	}
	
	public String getGameName(){
		return gameName;
	}
	
	public void setGameIcon(String filepath){
		gameIconFilePath = filepath;
	}
	
	public String getGameIcon(){
		return gameIconFilePath;
	}
	
	
}