package gamecreation.level;

import java.util.List;

import data.SpriteMakerModel;
import newengine.managers.conditions.Condition;

public interface ILevelData{
	public String getName();
	public Condition getWinningCondition();
	public Condition getLosingCondition();
	public double getSpawnTime();
	public List<SpriteMakerModel> getSpawners();
}
