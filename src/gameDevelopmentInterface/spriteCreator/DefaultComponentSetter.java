package gameDevelopmentInterface.spriteCreator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import data.DeveloperData;
import exception.UnsupportedTypeException;
import gameDevelopmentInterface.developerdata.ComponentSetter;
import helperAnnotations.ConstructorForDeveloper;
import newengine.sprite.component.Component;
/**
 * 
 * @author Daniel
 * A GUI component that allows the user to instantiate any component.
 * Looks for a method marked ConstructorForDeveloper, and and creates 
 * GUI setters to instantiate a class using the constructor's parameters.
 * 
 * I wrote a lot of duplicate code between the two methods- refactor later.
 * @param <T>
 */
public class DefaultComponentSetter<T extends Component> extends ComponentSetter{	
	private List<VariableSetter> fieldSetters;
	private Constructor<T> ctor;
	private DeveloperData data;
	
	public DefaultComponentSetter(Class<T> myComponent, DeveloperData data) throws UnsupportedTypeException{
		super(myComponent);
		this.data=data;
		fieldSetters=new ArrayList<>();
		ctor=getDeveloperConstructor(myComponent);		
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
	
	@SuppressWarnings("unchecked")
	public DefaultComponentSetter(T component, DeveloperData data) throws UnsupportedTypeException{
		super(component.getClass());
		this.data=data;
		fieldSetters=new ArrayList<>();
		ctor=getDeveloperConstructor(component.getClass());		
		if(ctor==null){
			return;
		}

		Parameter[] parameters=ctor.getParameters();
		VariableSetterFactory setterFactory=new VariableSetterFactory(data);
		for(int i=0;i<parameters.length;i++){
			VariableSetter setter=setterFactory.setterFromParameter(parameters[i]);
			setter.setField(component.getParameters()[i]);
			fieldSetters.add(setterFactory.setterFromParameter(parameters[i]));
		}
		fieldSetters.forEach((fieldSetter)->this.getChildren().add(fieldSetter));
	}
	
	public Constructor<T> getDeveloperConstructor(Class<? extends Component> component){
		Constructor<T> myCtor=null;
		Constructor<T>[] ctors=(Constructor<T>[]) component.getConstructors();
		for(Constructor<T> constructor:ctors){
			if(constructor.isAnnotationPresent(ConstructorForDeveloper.class)){
				myCtor=constructor;
				break;
			}
		}	
		return myCtor;
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
