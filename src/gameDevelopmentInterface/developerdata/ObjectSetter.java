package gameDevelopmentInterface.developerdata;

/**
 * A generic class that is used to provide a GUI to instantiate arbitrary objects.
 * Daniel
 */

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public abstract class ObjectSetter<T> extends VBox {
	private Class<? extends T> myType;
	
	public ObjectSetter(Class<? extends T> clazz){
		myType=clazz;
		setStyle(" -fx-background-color: white;");
	}
	
	protected void addDefaultLabel(){
		this.getChildren().add(new Label(myType.getSimpleName()));
	}

	public Class<? extends T> getObjectType(){
		return myType;
	}
	
	public abstract T produceObject() throws Exception;
}
