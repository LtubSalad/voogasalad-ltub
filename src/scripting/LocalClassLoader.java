package scripting;

import java.util.HashMap;
import java.util.Map;
import groovy.lang.GroovyClassLoader;


/**
 * A ClassLoader defines where to find classes when Java creates them.
 * 
 * This class allows for Java, XStream, and Groovy to share definitions, 
 * no matter where they were created.
 * 
 * @author Robert C. Duvall
 */
public class LocalClassLoader extends GroovyClassLoader {
    private Map<String, Class<?>> myClasses = new HashMap<>();

    /**
     * Add a new class definition to set of possible classes.
     */
    public void add (Class<?> c) {
        myClasses.put(c.getName(), c);
    }


    /**
     * @see GroovyClassLoader#loadClass(String)
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (myClasses.containsKey(name)) {
            return myClasses.get(name);
        }
        else {
            return super.loadClass(name);
        }
    }
}
