package gamecreation.level;

import javafx.beans.property.StringProperty;
import newengine.managers.conditions.ICondition;

public interface ILevelData{
	public String getName();
	public ICondition getWinningCondition();
	public ICondition getLosingCondition();
	public double getSpawnTime();
}
