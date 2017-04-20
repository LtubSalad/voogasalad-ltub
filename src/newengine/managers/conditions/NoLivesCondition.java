package newengine.managers.conditions;

public class NoLivesCondition extends Condition {

	@Override
	public boolean check() {
		return 0 == getPlayerStatsModel().getLives();
	}
}
 