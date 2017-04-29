package data;

import java.util.ArrayList;
import java.util.List;

import gamecreation.level.LevelData;

/**
 * @author tahiaemran
 * 
 * should merge this w/ developer data, just a dummy class
 *
 */
public class GameData {
	
	List<LevelData> levels; 
	List<SpriteMakerModel> sprites; 
	
	int numLives; 
	int numStartingGold; 
	
	public GameData(){
		levels = new ArrayList<LevelData>(); 
		sprites = new ArrayList<SpriteMakerModel>(); 
		
		numLives = 0; 
		numStartingGold = 0; 
	}
	
	public List<LevelData> getLevels(){
		return levels; 
	}
	
	public List<SpriteMakerModel> getSprites(){
		return sprites; 
	}
	
	public int getStartingLives(){
		return numLives; 
	}
	
	public int getStartingGold(){
		return numStartingGold; 
	}
}
