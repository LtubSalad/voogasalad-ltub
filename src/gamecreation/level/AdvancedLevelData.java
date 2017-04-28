package gamecreation.level;

import newengine.managers.conditions.Condition;

public class AdvancedLevelData implements LevelData {
	private String name;
	private double spawnTime;
	private double damageMulti;
	private Condition winningCondition;
	private Condition losingCondition;
	
	public AdvancedLevelData(String name, double spawnTime, double damageMultiplier){
		this.name = name;
		this.spawnTime = spawnTime;
		this.damageMulti = damageMultiplier;
	}
	

	@Override
	public String getName() {
		return name;
	}


	@Override
	public double getSpawnTime() {
		return spawnTime;
	}

	@Override
	public double getDamageMultiplier() {
		return damageMulti;
	}


	@Override
	public Condition getWinningCondition() {
		return winningCondition;
	}

	@Override
	public Condition getLosingCondition() {
		return losingCondition;
	}


	@Override
	public void setName(String name) {
		this.name = name;
	}

}
