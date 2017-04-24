package gamecreation.level;

public class AdvancedLevelData implements LevelData {
	private String name;
	private int totalMonsters;
	private double spawnTime;
	private double damageMulti;
	
	public AdvancedLevelData(String name, int totalMonsters, double spawnTime, double damageMultiplier){
		this.name = name;
		this.totalMonsters = totalMonsters;
		this.spawnTime = spawnTime;
		this.damageMulti = damageMultiplier;
	}
	

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getTotalMonsters() {
		return totalMonsters;
	}

	@Override
	public double getSpawnTime() {
		return spawnTime;
	}

	@Override
	public double getDamageMultiplier() {
		return damageMulti;
	}

}
