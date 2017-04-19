package newengine.managers.conditions;

public class GoldMinimumCondition extends Condition{
	private int goldMin;
	
	public GoldMinimumCondition(int minimumGold){
		this.goldMin = minimumGold;
	}

	@Override
	public boolean check() {
		return (getPlayerStatsModel().getGold() > goldMin);
	}
	
	public void setMinimumGold(int minimumGold){
		this.goldMin = minimumGold;
	}

}
