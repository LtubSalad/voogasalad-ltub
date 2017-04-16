package newengine.model;

import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import engine.action.events.MoveSpriteEvent;
import newengine.event.input.GameWorldMouseEvent;
import newengine.model.PlayerSelectionState.SelectionType;
import newengine.skill.PlayerCreateSpriteSkill;
import newengine.skill.Skill;
import newengine.sprite.Sprite;
import newengine.sprite.image.ImageSet;
import newengine.utils.image.LtubImage;
import newengine.utils.point.GamePoint;
import newengine.utils.variable.Var;
import newengine.utils.variable.VarKey;
import newengine.utils.variable.VarMap;

public class PlayerSelectionState {

	public enum SelectionType {
		EMPTY, SINGLE, GROUP
	}
	
	private EventBus bus;
	private VarMap varMap;
	private Var<Sprite> spriteVar;
//	private Sprite sprite;
	private List<Sprite> sprites;
	private SelectionType selectionType = SelectionType.EMPTY;
	
	public PlayerSelectionState(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	public PlayerSelectionState(EventBus bus, VarMap varMap) {
		this.bus = bus;
		this.varMap = varMap;
		spriteVar = new Var<Sprite>();
		varMap.put(VarKey.SELECTED_SPRITE, spriteVar);
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
				bus.emit(new MoveSpriteEvent(MoveSpriteEvent.RAW, e.getActionMode(), e.getPlayer(), spriteVar.get(), e.getTarget()));
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
		spriteVar.set(sprite);
		selectionType = SelectionType.SINGLE;
	}
	public Sprite getSelectedSprite() {
		return spriteVar.get();
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
