package newengine.managers.conditions;

public class NoLivesCondition extends Condition {

	@Override
	public boolean check() {
		if (this.getPlayerStatsModel().getLives(this.getPlayerRelationModel().getMainPlayer()).isPresent()) {
			return 0 == this.getPlayerStatsModel().getLives(this.getPlayerRelationModel().getMainPlayer()).get();
		}
		return false;
	}
}
 