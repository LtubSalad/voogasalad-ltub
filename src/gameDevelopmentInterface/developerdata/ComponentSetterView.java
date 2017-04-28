package gameDevelopmentInterface.developerdata;


import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;

public abstract class  ComponentSetterView<T extends Component> extends VBox {
	private Class<T> myType;
	
	public ComponentSetterView(Class<T> clazz){
		setStyle(" -fx-background-color: white;");
		myType=clazz;
		this.getChildren().add(new Label("Component: "+clazz.getSimpleName()));
	}

	public Class<T> getComponentType(){
		return myType;
	}
	
	public abstract T produceComponent() throws Exception;
}
