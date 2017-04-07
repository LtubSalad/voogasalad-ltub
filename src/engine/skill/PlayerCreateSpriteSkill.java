package engine.skill;

import java.util.Optional;

import bus.EventBus;
import commons.Point;
import engine.model.SpriteModelEvent;
import engine.sprite.Sprite;

public class PlayerCreateSpriteSkill implements Skill {

	private EventBus bus;
	private Sprite sprite;
	private Target target;

	public PlayerCreateSpriteSkill(EventBus bus, Sprite sprite) {
		this.bus = bus;
		this.sprite = sprite;
	}

	@Override
	public void trigger(Point pos) {
		sprite.setPos(pos);
		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, sprite));
	}

	/**
	 * If instant, trigger directly when selected
	 */
	@Override
	public boolean isInstant() {
		// TODO
		return false;
	}

	@Override
	public void setTarget(Target target) {
		this.target = target;
	}
	@Override
	public Optional<Target> getTarget() {
		return Optional.ofNullable(target);
	}

}
