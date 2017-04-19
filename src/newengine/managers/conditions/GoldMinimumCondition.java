package newengine.managers.conditions;

public class GoldMinimumCondition extends Condition{
	private int goldMin;
	public static final String GOLD_VAR = "Gold";
	
	public GoldMinimumCondition(int minimumGold){
		this.goldMin = minimumGold;
	}

	@Override
	public boolean check() {
		//TODO check if map has that type
		return (getPlayerStatsModel().getWealth(GOLD_VAR) > goldMin);
	} 
	
	public void setMinimumGold(int minimumGold){
		this.goldMin = minimumGold;
	}

}
