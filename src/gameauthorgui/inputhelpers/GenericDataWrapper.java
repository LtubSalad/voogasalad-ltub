package gameauthorgui.inputhelpers;

/**
 * NOT USED, but just an implementation of DataWrapper
 * @author Matthew Tribby
 * @param <E>
 */
public class GenericDataWrapper<E> implements DataWrapper{
	private E e;
	private String type;
	
	/**
	 * Type of data wrapper
	 * @param e
	 */
	public GenericDataWrapper(E e){
		this.e = e;
	}
	
	/**
	 * Gets string of type
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Gets object e
	 */
	public  E get(){
		return e;
	}

	/**
	 * returns class
	 * @return
	 */
	public Class<? extends Object> checkType(){
		return e.getClass();
	}
}
