package newengine.model;

/**
 * A container for all the models. For easier data query.
 * @author keping
 *
 */
public class Models {

	private final SpriteModel spriteModel;
	private final PlayerStatsModel playerStatsModel;
	private final SelectionModel selectionModel;
	
	public Models(SpriteModel spriteModel, PlayerStatsModel playerStatsModel, SelectionModel selectionModel) {
		this.spriteModel = spriteModel;
		this.playerStatsModel = playerStatsModel;
		this.selectionModel = selectionModel;
	}
	
	public SpriteModel spriteModel() {
		return spriteModel;
	}
	public PlayerStatsModel playerStatsModel() {
		return playerStatsModel;
	}
	public SelectionModel selectionModel() {
		return selectionModel;
	}
	
}
