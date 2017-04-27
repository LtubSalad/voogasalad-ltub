package newengine.sprite.components;

import gameDevelopmentInterface.Path;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.DeveloperMethod;
import helperAnnotations.VariableName;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class PathFollower extends Component{
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
		return null;
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
