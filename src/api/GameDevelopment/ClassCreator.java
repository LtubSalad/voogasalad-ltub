package api.GameDevelopment;

import java.io.File;

import api.Data.InterfaceData;
import javafx.*;
/**
 * 
 * @author Jake Daniel, 
 * Takes in data concerning interface data from the interface popup box and puts it in a 
 * xStream-readable format. Gives this data to a XML file producer when needed.
 */
public interface ClassCreator {
	//Put in attribute data from an attribute
	public void putInterfaceData(InterfaceData data);
	
	//Produce XML file for this class' data.
	public File createClassData();
}
