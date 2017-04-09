package engine.skill;

import java.util.Optional;

import engine.sprite.Sprite;

public interface Skill {

	public void setInstant(boolean isInstant);
	public boolean isInstant();

	public void setSource(Sprite sprite);
	public Optional<Sprite> getSource();
	public void setTarget(Target target);
	public Optional<Target> getTarget();
	public void trigger();

}
