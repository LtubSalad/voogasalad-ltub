package newengine.sprite.components;

import commons.point.GamePoint;
import gameDevelopmentInterface.Path;
import helperAnnotations.DeveloperMethod;
import newengine.events.QueueEvent;
import newengine.events.sprite.MoveEvent;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class PathFollower extends Component{
	public static final ComponentType<PathFollower> TYPE = new ComponentType<>(PathFollower.class.getName());
	private Path path;
	
	public PathFollower(Path path){
		this.path=path;
	}
	
	@DeveloperMethod
	public void followPath(){
		
	}
	
	public void afterAdded(){
		if (path.getPath().poll() == null){
			return;
		}
		GamePoint curr = path.getPath().poll();
		while (curr != null){
			sprite.emit(new QueueEvent(QueueEvent.ADD, new MoveEvent(MoveEvent.START_POSITION, sprite, new Target(curr))));
			curr = path.getPath().poll();
		}
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Component clone() {
		return new PathFollower(path);
	}

}
