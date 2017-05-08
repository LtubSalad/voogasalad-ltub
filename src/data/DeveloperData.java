package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameDevelopmentInterface.Path;
import gamecreation.level.ILevelData;
import gamecreation.level.LevelData;
import gamecreation.level.SerializableLevelData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.util.Pair;
/**
 * 
 * @author Jake
 * Stores the information needed to specify how an entire game should be run, though it needs to be converted
 * to serializable version before xstream.
 * Also tracks info such as available paths and sprites that can be added to a screen for developer purposes. 
 */
public class DeveloperData {
	public static final String NUMBER_OF_LIVES = "NUM_LIVES";
	public static final String BUILD_TOWER = "BUILD_IN_GAME";
	public static final String NUMBER_OF_STARTING_GOLD = "NUM_GOLD";
	public static final String LEVEL_COMPLETION_BONUS = "LEVEL_COMPLETION";
	public static final String GAME_NAME = "GAME_NAME";
	public static final String GAME_ICON = "GAME_ICON";
	private ObservableMap<String,String> myData = FXCollections.observableHashMap();
	private ObservableList<LevelData> levelData; // need 
	private ObservableList<Path> myPaths;
	private List<SpriteMakerModel> availableTowers = new ArrayList<>();
	private ObservableList<SpriteMakerModel> backgroundTiles = FXCollections.observableArrayList();
	private ObservableList<SpriteMakerModel> backgroundTilesToDrag = FXCollections.observableArrayList();
	private Map<SpriteMakerModel, Boolean> onScreenOrNot = new HashMap<SpriteMakerModel, Boolean>();
	private int numRows = 6;
	private int numCols = 6;
	private ObservableList<SpriteMakerModel> mySprites; //need
	private SpritesForScreenUse sprites = new SpritesForScreenUse();  //need
 	
	public DeveloperData() {
		List<SpriteMakerModel> list=new ArrayList<SpriteMakerModel>();
		levelData = FXCollections.observableArrayList();
		mySprites=FXCollections.observableList(list);
		sprites=new SpritesForScreenUse();
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
	
	public void addTileToDrag(SpriteMakerModel tile) {
		backgroundTilesToDrag.add(tile);
	}
	
	public ObservableList<SpriteMakerModel> getTilesToDrag() {
		return backgroundTilesToDrag;
	}
	
	public ObservableList<SpriteMakerModel> getBackgroundTiles() {
		return backgroundTiles;
	}
	
	public Map<SpriteMakerModel, Boolean> getIfTileOnScreen() {
		return onScreenOrNot;
	}
	
	public void addBackgroundTile(SpriteMakerModel tile) {
		onScreenOrNot.put(tile, false);
		backgroundTiles.add(tile);
	}
	
	public void addTowerToModel(SpriteMakerModel tower) {
		availableTowers.add(tower);
	}
	
	public void addSprite(SpriteMakerModel sprite){
		sprites.addPossibleSprite(sprite);
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
	
	@Deprecated
	public SpritesForScreenUse getScreenSprites(){
		return sprites;
	}
	
	public ObservableList<LevelData> getLevelData(){
		return levelData;
	}
	
	public List<ILevelData> getReadOnlyLevelData(){
		List<ILevelData> readOnly = new ArrayList<ILevelData>();
		levelData.stream().forEach(e -> readOnly.add(new SerializableLevelData(e)));
		return readOnly;
	}
	
	/**
	 * Put in data representing variable-variable name pairs.
	 * @param data
	 */
	public void addData(Pair<String,String> data) {
		myData.put(data.getKey(), data.getValue());	
	}
	
	/**
	 * @return all of the data held in this model
	 */
	public ObservableMap<String,String> getAllData() {
		return myData;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows=numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public void setNumCols(int numCols) {
		this.numCols=numCols;
	}
	
	
}
