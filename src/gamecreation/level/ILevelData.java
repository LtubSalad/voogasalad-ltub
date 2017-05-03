package gamecreation.level;

import java.util.List;

import data.SpriteMakerModel;
import newengine.managers.conditions.Condition;

/**
 * This interface sets forth the expected behavior for a LevelData class. A LevelData class is supposed
 * to hold level specific data. This interface is specifically designed to be read-only, meaning that it
 * is the expectations set by the back-end when they are taking in data from the authoring environment
 * and need to get level-specific details. 
 * @author Matthew Tribby
 */
public interface ILevelData{
	/**
	 * Gets the name of the Level
	 * @return String representation of Level Name
	 */
	public String getName();
	
	/**
	 * Gets the winning condition of the level
	 * @return winning condition for this level
	 */
	public Condition getWinningCondition();
	
	/**
	 * Gets the losing condition of the level
	 * @return losing condition for this level
	 */
	public Condition getLosingCondition();
	
	/**
	 * Gets the time in between spawns for this level (The smaller, the faster the spawn rate)
	 * @return Seconds between spawn
	 */
	public double getSpawnTime();
	
	/**
	 * Returns a list of SpriteMakerModels which represent the Sprites that will be the Spawners for this
	 * specific levels. Spawners are level-specific. 
	 * @return List of Spawners in the form of SpriteMakerModel objects
	 */
	public List<SpriteMakerModel> getSpawners();
}
