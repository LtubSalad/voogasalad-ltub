package engine.spritecreation;

import java.io.File;
import java.util.List;

import bus.EventBus;
import data.AttributeData;
import utilities.XStreamHandler;

/**
 * @author tahiaemran
 *
 *Class that manages taking all data from the game authoring environment 
 * and creating the game from it 
 *
 *TODO this whole class is getting a revamp in sprint 2
 */
public class GameBuildingManager {
	private XStreamHandler fileHandler; 
	private List<AttributeData> fileAttributes; 
	private SpriteBuildingManager spriteBuilder;
	private EventBus bus;
	
	/**
	 * Creates GameBuildingManager instance with a corresponding event bus. This constructor creates an XStreamHandler and a
	 * SpriteBuildingManager which will be responsible for assembling game data
	 * @param bus
	 */
	public GameBuildingManager(EventBus bus){
		this.fileHandler = new XStreamHandler();
		this.spriteBuilder = new SpriteBuildingManager(bus);
		this.bus = bus;
	}
	
	/**
	 * Another constructor, independent of eventbus
	 * @param xSH XStreamHandler
	 * 
	 * TODO don't believe we need this constructor anymore
	 */
	public GameBuildingManager(XStreamHandler xSH) {
		this.fileHandler = xSH; 
	}
	
	/**
	 * Empty constructor
	 * 
	 * TODO don't believe we need this constructor
	 */
	public GameBuildingManager(){
		
	}
	
	/**
	 * Build a file's sprite currently
	 * @param file
	 */
	public void buildFromFile(File file){
		System.out.println(file.getName());
		this.fileAttributes = fileHandler.getScreenModel(file);
	}
	
//	public void printTestFile() {
//		for (AttributeData a : fileAttributes){
//			System.out.println("attribute name is " + a.getName());
//		}
//	}
	
	
	 
	
	
}
