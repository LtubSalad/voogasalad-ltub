package newengine.model;

/**
 * A container for all the models. For easier data query.
 * @author keping
 *
 */
public class Models {

	private final SpriteModel spriteModel;
	private final PlayerStatsModel playerStatsModel;
	private final PlayerRelationModel playerRelationModel;
	private final SelectionModel selectionModel;
	
	public Models(SpriteModel spriteModel, PlayerStatsModel playerStatsModel, 
			PlayerRelationModel playerRelationModel, SelectionModel selectionModel) {
		this.spriteModel = spriteModel;
		this.playerStatsModel = playerStatsModel;
		this.playerRelationModel = playerRelationModel;
		this.selectionModel = selectionModel;
	}
	
	public SpriteModel spriteModel() {
		return spriteModel;
	}
	public PlayerStatsModel playerStatsModel() {
		return playerStatsModel;
	}
	public PlayerRelationModel playerRelationModel() {
		return playerRelationModel;
	}
	public SelectionModel selectionModel() {
		return selectionModel;
	}
	
}
