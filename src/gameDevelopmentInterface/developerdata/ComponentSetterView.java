package gameDevelopmentInterface.developerdata;


import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;

public abstract class  ComponentSetterView<T extends Component> extends VBox {
	private Class<T> myType;
	
	public ComponentSetterView(Class<T> clazz){
		setStyle(" -fx-background-color: white;");
		myType=clazz;
	}
	
	public Class<T> getType(){
		return myType;
	}
	
	public abstract T produceComponent() throws Exception;
}
