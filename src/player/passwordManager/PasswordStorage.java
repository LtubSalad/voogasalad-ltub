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
public class PasswordStorage {
	private String userName;
	private String password;

 
    public static void main(String a[]) throws IOException{
         
        OutputStream os = null;
        Properties prop = new Properties();
        prop.setProperty("name", "java2novice");
        prop.setProperty("domain", "www.java2novice.com");
        prop.setProperty("email", "java2novice@gmail.com");
        try {
            os = new FileOutputStream("MyProp.properties");
            prop.store(os, "Dynamic Property File");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         
    }
    
    public void loadParams() {
        Properties props = new Properties();
        InputStream is = null;
     
        // First try loading from the current directory
        try {
            File f = new File("server.properties");
            is = new FileInputStream( f );
        }
        catch ( Exception e ) { is = null; }
     
        try {
            if ( is == null ) {
                // Try loading from classpath
                is = getClass().getResourceAsStream("server.properties");
            }
     
            // Try loading properties from the file (if found)
            props.load( is );
        }
        catch ( Exception e ) { }
    }
    
    //TODO:need to store the user registration
    public void saveParamChanges(String inputUser, String inputPassword) {
        try {
            Properties props = new Properties();
            props.setProperty("ServerAddress", userName);
            props.setProperty("ServerPort", password);
            File f = new File("myProp.properties");
            OutputStream out = new FileOutputStream( f );
            props.store(out, "This is an optional header comment string");
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }
    
}