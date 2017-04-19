package newengine.managers.conditions;

import newengine.model.PlayerStatsModel;
import newengine.model.SpriteModel;

public class NoLivesCondition extends Condition {
	private SpriteModel spriteModel;
	private PlayerStatsModel playerStatsModel;

	@Override
	public boolean check() {
		return 0 == playerStatsModel.getLives();
	}
}
