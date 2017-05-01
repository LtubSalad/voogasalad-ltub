package gameDevelopmentInterface.developerdata;


import javafx.scene.Parent;
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
