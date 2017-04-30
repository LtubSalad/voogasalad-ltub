package gameDevelopmentInterface.spriteCreator.variableSetters;

import java.util.ArrayList;
import java.util.List;

import exception.UnsupportedTypeException;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

/**
 * 
 * @author Daniel
 * Set an enum.
 * @param <T>
 */
public class EnumSetter<T extends Enum<?>> extends VariableSetter<T>{
	private ChoiceBox<T> enumChoices;
	
	public EnumSetter(Class<T> clazz,String description){
		super(clazz,description);
		T[] enumConstants=clazz.getEnumConstants();
		List<T> list=new ArrayList<>();
		for(int i=0; i< enumConstants.length;i++){
			list.add(enumConstants[i]);
		}
	
		enumChoices=new ChoiceBox<>();		
		enumChoices.setItems(FXCollections.observableList(list));
		this.getChildren().add(enumChoices);
	}
	
	@Override
	public T getValue() {
		return enumChoices.getValue();
	}

	@Override
	public void setField(T initialValue) {
		enumChoices.getSelectionModel().select(initialValue);
		System.out.println("set field:" +initialValue);
		System.out.println(enumChoices.getValue());
		
	}

}
