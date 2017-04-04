package engine.model;

import java.util.ArrayList;
import java.util.List;

import commons.RunningMode;
import engine.gameloop.LoopComponent;
import engine.input.PlayerSelectionState;
import engine.sprite.Sprite;

public class BasicModel implements Model {

	
	private List<Sprite> sprites = new ArrayList<>();
	private PlayerSelectionState selectionState;

	
	@Override
	public List<Sprite> getSprites() {
		return sprites;
	}
	@Override
	public void addSprite(Sprite sprite) {
		if (sprite == null && RunningMode.DEV_MODE) {
			System.out.println("Model received null sprite: "+sprite);
		}
		if (sprite != null) {
			sprites.add(sprite);
		}
	}
	@Override
	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}

	@Override
	public LoopComponent getLoopComponent() {
		return (dt) -> {
			for (Sprite sprite : sprites) {
				sprite.update(dt);
			}
		};
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
