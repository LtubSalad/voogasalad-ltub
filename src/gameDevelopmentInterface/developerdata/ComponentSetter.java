package gameDevelopmentInterface.developerdata;


import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;

public abstract class  ComponentSetter<T extends Component> extends VBox {
	private Class<T> myType;
	
	public ComponentSetter(Class<T> clazz){
		myType=clazz;
		setStyle(" -fx-background-color: white;");
	}
	
	protected void addDefaultLabel(){
		this.getChildren().add(new Label("Component: "+myType.getSimpleName()));
	}

	public Class<T> getComponentType(){
		return myType;
	}
	
	public abstract T produceComponent() throws Exception;
}
