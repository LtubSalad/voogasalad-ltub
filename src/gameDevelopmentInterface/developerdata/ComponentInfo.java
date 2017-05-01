package gameDevelopmentInterface.developerdata;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bus.BusEvent;
import helperAnnotations.DeveloperMethod;
import newengine.sprite.component.Component;

public class ComponentInfo<T extends Component> {
	private Class<? extends Component> myComponent;
	public <T extends Component> ComponentInfo(Class<T> clazz){
		myComponent=clazz;
	}
	
	public List<Method> getDeveloperMethods(){
		List<Method> methods=new ArrayList<>();
		for(Method method:myComponent.getDeclaredMethods()){
			if(method.isAnnotationPresent(DeveloperMethod.class)){
				methods.add(method);
			}
		}
		return methods;
	}
	/*
	public ComponentSetterView<T> createSetter() throws UnsupportedTypeException{
		return new SimpleComponentSetter<T>(myComponent);
		
	}*/
	
	public Collection<BusEvent> getListenedEvents(){
		return null;
	}

}
