package gamecreation.level;

import java.util.List;

import data.SpriteMakerModel;
import newengine.managers.conditions.ICondition;

/**
 * The necessity of this class is that LevelData in its current implementation is observable for front-end 
 * purposes. However, with XStream serialization, observables add a very significant amount of length to xml files
 * and so it is pretty for serialization to have non-observable data being passed through XStream. This class offers
 * that and it also offers the interface of ILevelData. 
 */
public class SerializableLevelData implements ILevelData {
	private String name;
	private ICondition winning;
	private ICondition losing;
	private double spawnTime;
	private List<SpriteMakerModel> spawners;
	
	
	public SerializableLevelData(LevelData data){
		this.name = data.getName();
		this.winning = data.getWinningCondition();
		this.losing = data.getLosingCondition();
		this.spawnTime = data.getSpawnTime();
		this.spawners = data.getSpawners();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ICondition getWinningCondition() {
		return winning;
	}

	@Override
	public ICondition getLosingCondition() {
		return losing;
	}

	@Override
	public double getSpawnTime() {
		return spawnTime;
	}

	@Override
	public List<SpriteMakerModel> getSpawners() {
		return spawners;
	}

}
