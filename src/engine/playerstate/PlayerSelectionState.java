package engine.playerstate;

import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import engine.action.events.MoveSpriteEvent;
import engine.camera.GamePoint;
import engine.input.events.GameWorldMouseEvent;
import engine.skill.PlayerCreateSpriteSkill;
import engine.skill.Skill;
import engine.sprite.Sprite;
import engine.sprite.collidable.Collidable;
import engine.sprite.collidable.CollisionBound;
import engine.sprite.images.ImageSet;
import engine.sprite.images.LtubImage;
import engine.sprite.movable.Movable;

public class PlayerSelectionState {

	public enum SelectionType {
		EMPTY, SINGLE, GROUP
	}
	
	private EventBus bus;
	private Sprite sprite;
	private List<Sprite> sprites;
	private SelectionType selectionType = SelectionType.EMPTY;
	
	public PlayerSelectionState(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(GameWorldMouseEvent.SELECT_SPRITE, (e) -> {
			e.getTarget().getSprite().ifPresent((sprite) -> {
				setSelectedSprite(sprite);
			});
		});
		bus.on(GameWorldMouseEvent.CANCEL_SKILL_AND_MOVE_SPRITE, (e) -> {
			if (selectionType == SelectionType.SINGLE) {
				// TODO: where comes the current player
				bus.emit(new MoveSpriteEvent(MoveSpriteEvent.RAW, e.getActionMode(), e.getPlayer(), sprite, e.getTarget()));
			}
			// TODO group selection
		});
	}
	
	public List<Skill> getAvailableSkills() {
		// TODO different skills according to the current selected sprite
		List<Skill> availableSkills = new ArrayList<>();
		Sprite sprite = new Sprite();
		sprite.setPos(new GamePoint(100, 100));
		LtubImage image = new LtubImage("images/characters/bahamut_left.png");
		ImageSet imageSet = new ImageSet();
		imageSet.setImage(image);
		sprite.setImageSet(imageSet);
		//Movable movable = new Movable(sprite);
		// TODO: FIX
	//	sprite.setMovable(movable);
	//	sprite.setCollidable(new Collidable(new CollisionBound(image)));
		availableSkills.add(new PlayerCreateSpriteSkill(bus, sprite));
		return availableSkills;
	}
	
	private void setSelectedSprite(Sprite sprite) {
		this.sprite = sprite;
		selectionType = SelectionType.SINGLE;
	}
	public Sprite getSelectedSprite() {
		return sprite;
	}
	public void setSelectedSprites(List<Sprite> sprites) {
		sprites = new ArrayList<>(sprites);
		selectionType = SelectionType.GROUP;
	}
	public List<Sprite> getSelectedSprites() {
		return new ArrayList<Sprite>(sprites);
	}
	public void clearSelection() {
		selectionType = SelectionType.EMPTY;
	}
	public SelectionType getSelectionType() {
		return selectionType;
	}
	
}
