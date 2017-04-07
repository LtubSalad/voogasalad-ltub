package engine.skill;

import java.util.Optional;

import bus.EventBus;
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
	@Override
	public void trigger() {
		// TODO: currently assume the target is a location
		sprite.setPos(target.getLocation().get());
		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, sprite));
	}

}
