package gameDevelopmentInterface.spriteCreator;

import exception.UnsupportedTypeException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Daniel
 * Only works for primitive variables.
 * @param <T>
 */
public class PrimitiveVariableSetter extends HBox{
	private TextField value;
	Class<?> type;
	public PrimitiveVariableSetter(Class<?> type,String descriptor) throws UnsupportedTypeException{
		this.type=type;
		value=new TextField();
		this.getChildren().addAll(new Label(type.getName()),new Label(descriptor), value);
	}
	
	@SuppressWarnings("unchecked")
	public Object getValue() throws UnsupportedTypeException{
		PrimitiveConverter converter=new PrimitiveConverter();
		return converter.convertString(type, value.getText());
	}

}

