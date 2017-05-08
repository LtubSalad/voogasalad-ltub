package gameauthorgui.inputhelpers;

/**
 * Interface for the ParameterInput, generic type
 * GUI component
 * @author Matthew Tribby
 * @param <E>
 */
public interface ParameterInput<E> {

	/**
	 * Gets value of the parameter input
	 * @return E object
	 */
	public E getValue();
	
	/**
	 * Gets type of parameter input
	 * @return
	 */
	public String getType();
}
