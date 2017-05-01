package newengine.sprite.components;

import commons.point.GamePoint;
import gameDevelopmentInterface.Path;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.DeveloperMethod;
import helperAnnotations.VariableName;
import newengine.events.QueueEvent;
import newengine.events.debug.SysPrintEvent;
import newengine.events.sprite.MoveEvent;
import newengine.events.stats.ChangeLivesEvent;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class PathFollower extends Component{

	public static final ComponentType<PathFollower> TYPE = new ComponentType<>(PathFollower.class.getName());
	private Path path;
	private GamePoint finalPoint = new GamePoint();
	
	@ConstructorForDeveloper
	public PathFollower(@VariableName(name = "SelectedPath") Path path){
		this.path=path;
	}

	
	@DeveloperMethod
	public void followPath(){
		GamePoint curr = new GamePoint();
		if (!path.getPath().isEmpty()){
			curr = path.getPath().poll();
			sprite.emit(new QueueEvent(QueueEvent.ADD, new MoveEvent(MoveEvent.START_POSITION, sprite, new Target(curr))));
		}
	}
	
	public Path getPath() {
		return this.path;
	}
	
	public void afterAdded(){
		if (path.getPath().poll() == null){
			return;
		}
		GamePoint curr = new GamePoint();
		while (!path.getPath().isEmpty()){
			curr = path.getPath().poll();
			sprite.emit(new QueueEvent(QueueEvent.ADD, new MoveEvent(MoveEvent.START_POSITION, sprite, new Target(curr))));
		}
		finalPoint = curr;
		System.out.println("the final point is " + finalPoint.x() + " " + finalPoint.y());
		
		
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
	public Object[] getGUIParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	public GamePoint getFinalPoint() {
		return finalPoint;
	}
	
}
