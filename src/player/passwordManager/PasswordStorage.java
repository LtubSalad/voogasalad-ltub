/**
 * 
 */
package player.passwordManager;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author Zhiyong
 *
 */
public class PasswordStorage implements Writer{


	@Override
	public void write(String key, String data) {
		Properties configProperty = new Properties();
		configProperty.setProperty(key, data);
		File file = new File("src/resources/password.properties");
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file, true);
			configProperty.store(fileOut, "sample properties");
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}