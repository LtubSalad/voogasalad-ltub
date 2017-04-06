package engine.playerstate;

import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import engine.sprite.Sprite;

public class PlayerSelectionState {

	public enum SelectionType {
		EMPTY, SINGLE, GROUP
	}
	
	private EventBus bus;
	private Sprite sprite;
	private List<Sprite> sprites;
	private SelectionType type = SelectionType.EMPTY;
	
	public PlayerSelectionState(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		
	}
	
	
	public void setSelectedSprite(Sprite sprite) {
		this.sprite = sprite;
		type = SelectionType.SINGLE;
	}
	public Sprite getSelectedSprite() {
		return sprite;
	}
	public void setSelectedSprites(List<Sprite> sprites) {
		sprites = new ArrayList<>(sprites);
		type = SelectionType.GROUP;
	}
	public List<Sprite> getSelectedSprites() {
		return new ArrayList<Sprite>(sprites);
	}
	public void clearSelection() {
		type = SelectionType.EMPTY;
	}
	public SelectionType getSelectionType() {
		return type;
	}
	
}
