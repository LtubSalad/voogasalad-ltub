/**
 * 
 */
package player.passwordManager;

/**
 * @author Zhiyong
 *
 */
public interface Writer {
	
	/**
	 * write the information in some files
	 * using the streams in java
	 */
	public void write(String key, String value);

}
