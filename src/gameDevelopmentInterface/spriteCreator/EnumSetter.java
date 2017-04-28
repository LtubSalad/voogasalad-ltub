package gameDevelopmentInterface.spriteCreator;

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
public class EnumSetter<T extends Enum> extends VariableSetter<T>{
	private T selectedEnum;
	
	public EnumSetter(Class<T> clazz,String description){
		this.getChildren().add(produceLabel(description));
		T[] enumConstants=clazz.getEnumConstants();
		List<T> list=new ArrayList<>();
		for(int i=0; i< enumConstants.length;i++){
			list.add(enumConstants[i]);
		}
	
		ChoiceBox<T> enumChoices=new ChoiceBox<>();		
		enumChoices.setItems(FXCollections.observableList(list));
		
		enumChoices.selectionModelProperty().addListener((invalidation)->{
			selectedEnum=enumChoices.getValue();
		});
		this.getChildren().add(enumChoices);
	}
	
	@Override
	public T getValue() {
		// TODO Auto-generated method stub
		return selectedEnum;
	}

}
