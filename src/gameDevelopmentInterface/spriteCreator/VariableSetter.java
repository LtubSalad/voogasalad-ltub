package gameDevelopmentInterface.spriteCreator;

import exception.UnsupportedTypeException;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Daniel
 * An interface designed to produce an arbitrary variable.
 * @param <T>
 */
public abstract class VariableSetter<T> extends HBox{
	private double PREF_LABEL_WIDTH=200;
	private double PREF_LABEL_HEIGHT=50;
	
	public VariableSetter(Class<T> clazz, String variableName){
		Label label=produceLabel(clazz.getSimpleName());
		Label descriptorLabel=produceLabel(variableName);
		this.getChildren().addAll(label,descriptorLabel);
	}
	
	public abstract T getValue() throws UnsupportedTypeException, Exception;
	
	public abstract void setInitialField(T initialValue);

	private Label produceLabel(String name){
		Label label=new Label(name);
		label.setPrefSize(PREF_LABEL_WIDTH, PREF_LABEL_HEIGHT);
		return label;
	}
}
