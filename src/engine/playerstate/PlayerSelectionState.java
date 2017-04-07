package engine.playerstate;

import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import commons.Point;
import engine.skill.PlayerCreateSpriteSkill;
import engine.skill.Skill;
import engine.sprite.LtubImage;
import engine.sprite.Movable;
import engine.sprite.Sprite;
import engine.sprite.collision.Collidable;
import engine.sprite.collision.CollisionBound;

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
	
	private void initHandlers() { }
	
	public List<Skill> getAvailableSkills() {
		// TODO different skills according to the current selected sprite
		List<Skill> availableSkills = new ArrayList<>();
		Sprite sprite = new Sprite();
		sprite.setPos(new Point(100, 100));
		LtubImage image1 = new LtubImage("images/characters/bahamut_left.png");
		sprite.setImage(image1);
		Movable movable1 = new Movable(sprite);
		sprite.setMovable(movable1);
		sprite.setCollidable(new Collidable(new CollisionBound(image1)));
		availableSkills.add(new PlayerCreateSpriteSkill(bus, sprite));
		return availableSkills;
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
