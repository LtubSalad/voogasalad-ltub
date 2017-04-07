package engine.skill;

import java.util.Optional;

public interface Skill {

	public boolean isInstant();

	public void setTarget(Target target);
	public Optional<Target> getTarget();
	public void trigger();

}
