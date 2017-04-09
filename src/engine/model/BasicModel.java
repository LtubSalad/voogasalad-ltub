package engine.model;

import java.util.Collection;
import java.util.List;

import commons.RunningMode;
import engine.gameloop.LoopComponent;
import engine.model.SpriteModel.SpriteHandler;
import engine.playerstate.PlayerSelectionState;
import engine.sprite.Sprite;


// also needs to implement statsModel
public class BasicModel implements Model, IStatsModel, ISpriteModel<Sprite> {

	private PlayerSelectionState selectionState;
	private SpriteModel spriteModel; 
	private SpriteHandler handler; 
	
	int points; 
	int lives; 
	int health; 
	double timeRemaining; 
	int bonuses; 
	
	public BasicModel(){
		spriteModel = new SpriteModel(); 
		handler = (SpriteHandler) spriteModel.getHandler();
		
		points = 0; 
		lives = 0; 
		health = 100; // need default value 
		timeRemaining = 0; // need default value 
		bonuses = 0; // need default value 
	
	}
	
	public BasicModel(SpriteModel spriteModel){
		this.spriteModel = spriteModel; 
		handler = (SpriteHandler) spriteModel.getHandler();
	}

	@Override
	public void update(double dt) {
		Collection<Sprite> sprites = handler.getSprites();
		for (Sprite sprite : sprites) {
			sprite.update(dt);
		}
	}

	@Override
	public PlayerSelectionState getPlayerSelectionState() {
		return selectionState;
	}
	@Override
	public void setPlayerSelectionState(PlayerSelectionState selectionState) {
		this.selectionState = selectionState;
	}



	@Override
	public List<Sprite> getSprites() {
		return (List<Sprite>) handler.getSprites(); 
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public int getLives() {
		return lives; 
	}

	@Override
	public int getHealth() {
		return health; 
	}

	@Override
	public double getTimeRemaining() {
		return timeRemaining; 
	}

	@Override
	public int getBonuses() {
		return bonuses; 
	}

	@Override
	public void addSprite(Sprite sprite) {
		handler.addSprite(sprite);
		
	}

	@Override
	public void removeSprite(Sprite sprite) {
		handler.removeSprite(sprite);
	}

}
