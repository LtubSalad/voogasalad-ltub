package newengine.managers.conditions;

import newengine.model.PlayerStatsModel.WealthType;

public class GoldMinimumCondition extends Condition{
	private int goldMin;
	public static final WealthType TYPE = WealthType.GOLD;
	
	public GoldMinimumCondition(int minimumGold){
		this.goldMin = minimumGold;
	}

	@Override
	public boolean check() {
		if (this.getPlayerStatsModel().getWealth(this.getPlayerRelationModel().getMainPlayer()).isPresent()) {
			System.out.println("goldMin " + goldMin);
			System.out.println("Gold for comaprison " + this.getPlayerStatsModel().getWealth(this.getPlayerRelationModel().getMainPlayer()).get().get(TYPE));
			System.out.println(this.getPlayerStatsModel().getWealth(this.getPlayerRelationModel().getMainPlayer()).get().get(TYPE) > goldMin);
			return this.getPlayerStatsModel().getWealth(this.getPlayerRelationModel().getMainPlayer()).get().get(TYPE) > goldMin;
		}
		return false;
	} 
	
	public void setMinimumGold(int minimumGold){
		this.goldMin = minimumGold;
	}

}
