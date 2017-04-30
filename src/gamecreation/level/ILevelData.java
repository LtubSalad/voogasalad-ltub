package gamecreation.level;

import java.util.List;

import data.SpriteMakerModel;
import newengine.managers.conditions.ICondition;

public interface ILevelData{
	public String getName();
	public ICondition getWinningCondition();
	public ICondition getLosingCondition();
	public double getSpawnTime();
	public List<SpriteMakerModel> getSpawners();
}
