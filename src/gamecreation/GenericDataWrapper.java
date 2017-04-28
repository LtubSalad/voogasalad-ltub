package gamecreation;

public class GenericDataWrapper<E> implements DataWrapper{
	private E e;
	private String type;
	
	public GenericDataWrapper(E e){
		this.e = e;
	}
	
	
	public String getType(){
		return type;
	}
	
	public  E get(){
		return e;
	}

	public Class<? extends Object> checkType(){
		return e.getClass();
	}
}
