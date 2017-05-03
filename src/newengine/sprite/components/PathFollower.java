package newengine.sprite.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import commons.point.GamePoint;
import gameDevelopmentInterface.Path;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.DeveloperMethod;
import helperAnnotations.VariableName;
import newengine.events.QueueEvent;
import newengine.events.sprite.MoveEvent;
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
		
	}
	public Path getPath() {
		return this.path;
	}
	
	public void afterAdded(){
		if (path.getPath().peek() == null){
			return;
		}
		GamePoint curr = new GamePoint();
		Stack<GamePoint> holder = new Stack<>();
		while (!path.getPath().isEmpty()){
			curr = path.getPath().poll();
			holder.push(curr);
			sprite.emit(new QueueEvent(QueueEvent.ADD, new MoveEvent(MoveEvent.START_POSITION, sprite, new Target(curr))));
			System.out.println(curr.x() + " " + curr.y());
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


	public GamePoint getFinalPoint() {
		return finalPoint;
	}


	@Override
	public Object[] getGUIParameters() {
		// TODO Auto-generated method stub
		Object[] parameters=new Object[1];
		parameters[0]=path;
		return parameters;
	}
	
}
