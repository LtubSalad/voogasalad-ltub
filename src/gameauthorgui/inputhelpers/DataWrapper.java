package gameauthorgui.inputhelpers;

/**
 * NOT BEING USED IN GAME, but basically this wrapper could be used to hold any type and still contain the
 * type information. This would allow you to pass many different types in a list of DataWrappers. 
 * @author Matthew Tribby
 */
public interface DataWrapper {
	
	/**
	 * Gets type of data
	 * @return type String
	 */
	public String getType();
	
	/**
	 * Returns object held
	 * @return Object
	 */
	public Object get();

}
