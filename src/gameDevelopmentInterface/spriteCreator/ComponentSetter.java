package gameDevelopmentInterface.spriteCreator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import data.DeveloperData;
import exception.UnsupportedTypeException;
import gameDevelopmentInterface.Path;
import gameDevelopmentInterface.developerdata.ComponentSetterView;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.sprite.component.Component;
/**
 * 
 * @author Daniel
 * A GUI component that allows the user to instantiate any component.
 * Looks for a method marked ConstructorForDeveloper, and and creates 
 * GUI setters to instantiate a class using the constructor's parameters.
 * @param <T>
 */
public class ComponentSetter<T extends Component> extends ComponentSetterView<T>{	
	private List<VariableSetter> fieldSetters;
	private Constructor<T> ctor;
	private DeveloperData data;
	
	public ComponentSetter(Class<T> myComponent, DeveloperData data) throws UnsupportedTypeException{
		super(myComponent);
		this.data=data;
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
		VariableSetterFactory setterFactory=new VariableSetterFactory(data);
		for(int i=0;i<parameters.length;i++){
			fieldSetters.add(setterFactory.setterFromParameter(parameters[i]));
		}
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
