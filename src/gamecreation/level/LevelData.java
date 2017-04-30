package gamecreation.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import data.SpriteMakerModel;
import javafx.beans.property.StringProperty;
import newengine.managers.conditions.Condition;
import newengine.managers.conditions.NoLivesCondition;
import newengine.managers.conditions.NoMonstersCondition;



public class LevelData extends Observable implements ILevelData {
	private String name;
	private Condition winningCondition;
	private Condition losingCondition;
	private double spawnTime;
	private List<SpriteMakerModel> spawners;

	public LevelData() {
		this.name = "Untitled Level";
		spawners = new ArrayList<SpriteMakerModel>();
	}

	public void setName(String name) {
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

	public void setWinningCondition(Condition condition){
		this.winningCondition = condition;
	}
	
	public void setLosingCondition(Condition condition){
		this.losingCondition = condition;
	}
	
	@Override
	public Condition getWinningCondition() {
		//return new NoMonstersCondition();
		return winningCondition;
	}

	@Override
	public Condition getLosingCondition() {
		//return new NoLivesCondition();
		return losingCondition;
	}

	public StringProperty getNameTextProperty() {
		return null;
	}

	public void subscribe(Observer o) {
		this.addObserver(o);
	}

	public void addSpawner(SpriteMakerModel spawner) {
		spawners.add(spawner);
	}

	@Override
	public List<SpriteMakerModel> getSpawners() {
		return spawners;
	}

}
