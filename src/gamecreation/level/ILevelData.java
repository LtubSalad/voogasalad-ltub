package gamecreation.level;

import javafx.beans.property.StringProperty;
import newengine.managers.conditions.Condition;

public interface ILevelData{

	public void setName(String name);
	public String getName();
	public StringProperty getNameTextProperty();
	public Condition getWinningCondition();
	public Condition getLosingCondition();
	public double getSpawnTime();
	public double getDamageMultiplier();
}
