package gamecreation.level;

import javafx.beans.property.StringProperty;
import newengine.managers.conditions.Condition;

public interface ILevelData{
	public String getName();
	public StringProperty getNameTextProperty();
	public Condition getWinningCondition();
	public Condition getLosingCondition();
	public double getSpawnTime();
}
