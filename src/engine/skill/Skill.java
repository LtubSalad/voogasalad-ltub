package engine.skill;

import java.util.Optional;

import commons.Point;


public interface Skill {

	public void trigger(Point pos);

	public void setTarget(Target target);
	public Optional<Target> getTarget();
	
	public boolean isInstant();
	
}
