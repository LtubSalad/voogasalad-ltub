package gamecreation.level;

import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.StringProperty;
import newengine.managers.conditions.ICondition;

public class LevelData extends Observable implements ILevelData {
	private String name;
	private ICondition winningCondition;
	private ICondition losingCondition;
	private double spawnTime;

	public LevelData(){
		this.name = "Untitled Level";
	}
	
	public void setName(String name){
		this.name = name;
		this.setChanged();
		this.notifyObservers(this.getName());
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setSpawnTime(double time) {
		this.spawnTime = time;
	}
	
	@Override
	public double getSpawnTime() {
		return spawnTime;
	}

	public void setWinningCondition(ICondition condition){
		this.winningCondition = condition;
	}
	
	@Override
	public ICondition getWinningCondition() {
		return winningCondition;
	}

	@Override
	public ICondition getLosingCondition() {
		return losingCondition;
	}


	public StringProperty getNameTextProperty() {
		return null;
	}

	public void subscribe(Observer o){
		this.addObserver(o);
	}



}
