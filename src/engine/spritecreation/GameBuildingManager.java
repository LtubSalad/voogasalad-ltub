package engine.spritecreation;

import java.util.List;

import data.AttributeData;
import utilities.XStreamHandler;

/**
 * @author tahiaemran
 *
 *Class that manages taking all data from the game authoring environment 
 * and creating the game from it 
 *
 */
public class GameBuildingManager {
	private XStreamHandler fileHandler; 
	private List<AttributeData> fileAttributes; 
	public GameBuildingManager(){
		this.fileHandler = new XStreamHandler();
		fileAttributes = fileHandler.getScreenModelFile();
		printTestFile();
	}
	public GameBuildingManager(XStreamHandler xSH) {
		this.fileHandler = xSH; 
	}
	public void printTestFile() {
		for (AttributeData a : fileAttributes){
			System.out.println("attribute name is " + a.getName());
		}
	}
	
	
	 
	
	
}
