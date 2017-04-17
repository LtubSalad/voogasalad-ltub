/**
 * 
 */
package imageprocess;

/**
 * @author Zhiyong
 *
 */
public interface Processor<T> {

	/**
	 * @param t
	 * @param scale
	 * resize the element t by given the scale of the second argument
	 */
	T resize(T t, double scale);


	/**
	 * @param t
	 * @param targetWidth
	 * @param targetHeight
	 * @param preserveRatio
	 * @return
	 * resize the element t to some specific width and height specified by the arguments
	 * targetWidth and TargetHeight
	 * preserveRation is used to specify whether the new element needs preserve the width
	 * and height ratio
	 */
	T resize(T t, double targetWidth, double targetHeight, boolean preserveRatio);


}
