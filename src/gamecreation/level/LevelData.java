package gamecreation.level;

import newengine.managers.conditions.Condition;

public interface LevelData {

	public void setName(String name);
	public String getName();
	public Condition getWinningCondition();
	public Condition getLosingCondition();
	public double getSpawnTime();
	public double getDamageMultiplier();
}
