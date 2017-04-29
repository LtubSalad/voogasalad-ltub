package newengine.sprite.components;

import gameDevelopmentInterface.Path;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.DeveloperMethod;
import helperAnnotations.VariableName;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class PathFollower extends Component{
	public static final ComponentType<Images> TYPE = new ComponentType<>(PathFollower.class.getName());
	private Path path;
	private double speed;
	
	@ConstructorForDeveloper
	public PathFollower(@VariableName(name = "SelectedPath") Path path){
		this.path=path;
	}
	
	@DeveloperMethod
	public void followPath(){
		
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return new PathFollower(path);
	}
	
	public Path getPath() {
		return path;
	}

	@Override
	public Object[] getParameters() {
		Object[] parameters=new Object[1];
		parameters[0]=path;
		// TODO Auto-generated method stub
		return parameters;
	}

}
