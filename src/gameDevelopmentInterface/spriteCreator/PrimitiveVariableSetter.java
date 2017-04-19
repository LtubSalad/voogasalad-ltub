package gameDevelopmentInterface.spriteCreator;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Daniel
 * Only works for primitive variables.
 * @param <T>
 */
public class PrimitiveVariableSetter<T> extends HBox{
	private TextField value;
	public PrimitiveVariableSetter(Class<T> type,String descriptor){
		value=new TextField();
		this.getChildren().addAll(new Label(type.getName()),new Label(descriptor), value);
	}
	
	@SuppressWarnings("unchecked")
	public T getValue(){
		return (T) value.getText();
	}

}

