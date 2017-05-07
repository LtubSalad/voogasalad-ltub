/**
 * 
 */
package player.passwordManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import utilities.MessageShowing;
import utilities.PopUpMessage;

/**
 * @author Zhiyong
 *Store the password and user name in a properties files
 *The store information is {username = password}
 */
public class PasswordStorage implements Writer{
	public static final String LOCATION = "src/resources/password.properties";

	@Override
	public void write(String key, String data) {
		Properties configProperty = new Properties();
		configProperty.setProperty(key, data);
		File file = new File(LOCATION);
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file, true);
			configProperty.store(fileOut, "sample properties");
			fileOut.close();
		} catch (IOException e) {
			PopUpMessage p = new MessageShowing();
			p.show("filenotfound");
		}
	}

}