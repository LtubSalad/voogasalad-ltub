package gamecreation;

public class GenericDataWrapper<E> implements DataWrapper{
	private E obj;
	private String type;
	
	public GenericDataWrapper(E e){
		this.obj = e;
	}
	
	public String getType(){
		return type;
	}
	
	public E get(){
		return obj;
	}

	public boolean checkType(){
		return String.class.isAssignableFrom(obj.getClass());
	}
}
