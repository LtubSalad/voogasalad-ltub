package gameDevelopmentInterface.spriteCreator;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Daniel
 * Only works for very simple variables.
 * @param <T>
 */
public class SimpleFieldSetter<T> extends HBox{
	private TextField value;
	public SimpleFieldSetter(Class<T> type,String descriptor){
		this.getChildren().addAll(new Label(type.getName()),new Label(descriptor));
		value=new TextField();
	}
	
	@SuppressWarnings("unchecked")
	public T getValue(){
		return (T)value.getText();
	}

}

