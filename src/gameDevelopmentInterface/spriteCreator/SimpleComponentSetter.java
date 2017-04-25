package gameDevelopmentInterface.spriteCreator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import exception.UnsupportedTypeException;
import gameDevelopmentInterface.developerdata.ComponentSetterView;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;
/**
 * 
 * @author Daniel
 * A GUI component that allows the user to instantiate any component, so long 
 * as it does not take parameters aside from enum, string or primitives.
 * @param <T>
 */
public class SimpleComponentSetter<T extends Component> extends ComponentSetterView<T>{	
	private List<VariableSetter> fieldSetters;
	private Constructor<T> ctor;
	
	public SimpleComponentSetter(Class<T> myComponent) throws UnsupportedTypeException{
		super(myComponent);
		fieldSetters=new ArrayList<>();
		Constructor<T>[] ctors=(Constructor<T>[]) myComponent.getConstructors();
		for(Constructor<T> constructor:ctors){
			if(constructor.isAnnotationPresent(ConstructorForDeveloper.class)){
				ctor=constructor;
				break;
			}
		}
		
		if(ctor==null){
			return;
		}

		Parameter[] parameters=ctor.getParameters();
		for(int i=0;i<parameters.length;i++){
			String name=parameters[i].getAnnotation(VariableName.class).name();
			if(parameters[i].getType().isPrimitive()||parameters[i].getType().equals(String.class)){
				fieldSetters.add(new SimpleVariableSetter(parameters[i].getType(),name));
			}else if(parameters[i].getType().isEnum()){
				fieldSetters.add(new EnumSetter(parameters[i].getType(),name));
			}
			else{
				throw new UnsupportedTypeException(parameters[i].getType());
			}
		}
		this.getChildren().add(new Label("Component: "+myComponent.getSimpleName()));
		fieldSetters.forEach((fieldSetter)->this.getChildren().add(fieldSetter));
	}

	@Override
	public T produceComponent() throws Exception{
		List<Object> parameters=new ArrayList<>();
		for(VariableSetter<?> setter:fieldSetters){
			parameters.add(setter.getValue());
		} 
		return ctor.newInstance(parameters.toArray());
	}
}
