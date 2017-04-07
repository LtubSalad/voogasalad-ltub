package engine.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import commons.RunningMode;
import engine.gameloop.LoopComponent;
import engine.model.SpriteModel.SpriteHandler;
import engine.playerstate.PlayerSelectionState;
import engine.sprite.Sprite;


// also needs to implement statsModel
public class BasicModel implements Model {

	private PlayerSelectionState selectionState;
	private SpriteModel spriteModel; 
	private SpriteHandler handler; 
	
	public BasicModel(){
		spriteModel = new SpriteModel(); 
		handler = (SpriteHandler) spriteModel.getHandler();
	
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

}
