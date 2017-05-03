package gameDevelopmentInterface.spriteCreator.variableSetters;

import exception.UnsupportedTypeException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Daniel
 * Only works for primitive variables and strings.
 * @param <T>
 */
public class SimpleVariableSetter<T> extends VariableSetter<T>{
	private TextField value;
	Class<T> type;
	
	public SimpleVariableSetter(Class<T> type,String descriptor) throws UnsupportedTypeException{
		super(type, descriptor);
		HBox box =new HBox();
		this.type=type;
		value=new TextField();
		this.getChildren().add(box);
		box.getChildren().addAll(value);
		box.setSpacing(50);
	}
	
	public T getValue() throws UnsupportedTypeException{
		PrimitiveConverter<T> converter=new PrimitiveConverter<T>();
		return converter.convertString(type, value.getText());
	}

	@Override
	public void setField(T initialValue) {
		value.setText(initialValue.toString());
	}
	
	public void setText(String test){
		value.setText(test);
	}

}

