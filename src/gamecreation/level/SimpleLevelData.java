package gamecreation.level;

import newengine.managers.conditions.Condition;

public class SimpleLevelData implements LevelData {
	private int diffMod; 
	private String name;
	private Condition winningCondition;
	private Condition losingCondition;

	public SimpleLevelData(){
		this.name = "Level";
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getSpawnTime() {
		return 10.0/diffMod;
	}

	@Override
	public double getDamageMultiplier() {
		return 5.0/diffMod;
	}

	@Override
	public Condition getWinningCondition() {
		return winningCondition;
	}

	@Override
	public Condition getLosingCondition() {
		return losingCondition;
	}

}
