package gameDevelopmentInterface.spriteCreator;

import exception.UnsupportedTypeException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Daniel
 * Only works for primitive variables and strings.
 * @param <T>
 */
public class SimpleVariableSetter<T> extends VariableSetter{
	private TextField value;
	Class<T> type;
	
	public SimpleVariableSetter(Class<T> type,String descriptor) throws UnsupportedTypeException{
		this.type=type;
		value=new TextField();
		this.getChildren().addAll(new Label(type.getSimpleName()),new Label(descriptor), value);
		this.setSpacing(20);
	}
	
	public T getValue() throws UnsupportedTypeException{
		PrimitiveConverter<T> converter=new PrimitiveConverter();
		return converter.convertString(type, value.getText());
	}

}

