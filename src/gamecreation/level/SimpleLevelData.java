package gamecreation.level;

public class SimpleLevelData implements LevelData {
	private int diffMod; 
	private String name;

	public SimpleLevelData(String name, int difficultyModifier){
		this.diffMod = difficultyModifier;
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getTotalMonsters() {
		return diffMod * 50;
	}
	
	@Override
	public double getSpawnTime() {
		return 10.0/diffMod;
	}

	@Override
	public double getDamageMultiplier() {
		return 5.0/diffMod;
	}

}
