package newengine.sprite.components;

import commons.point.GamePoint;
import gameDevelopmentInterface.Path;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.DeveloperMethod;
import helperAnnotations.VariableName;
import newengine.events.QueueEvent;
import newengine.events.debug.SysPrintEvent;
import newengine.events.sprite.MoveEvent;
import newengine.events.timer.DelayedEvent;
import newengine.events.sprite.MoveEvent;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class PathFollower extends Component{

	public static final ComponentType<PathFollower> TYPE = new ComponentType<>(PathFollower.class.getName());
	private Path path;
	
	@ConstructorForDeveloper
	public PathFollower(@VariableName(name = "SelectedPath") Path path){
		this.path=path;
	}

	
	@DeveloperMethod
	public void followPath(){
		
	}
	public Path getPath() {
		return this.path;
	}
	
	public void afterAdded(){
		if (path.getPath().poll() == null){
			return;
		}
		GamePoint curr = path.getPath().poll();
		while (curr != null){
			sprite.emit(new QueueEvent(QueueEvent.ADD, new MoveEvent(MoveEvent.START_POSITION, sprite, new Target(curr))));
//			sprite.emit(new QueueEvent(QueueEvent.ADD, new DelayedEvent(DelayedEvent.ANY, 10)));
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

	@Override
	public Object[] getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
