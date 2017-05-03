package gamecreation.level;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 * This is an abstract class providing a basic, bare-bones implementation of the ILevelCreator interface. 
 * In this class, the basics of the ILevelCreator interface are created so that future extensions will not
 * have to worry about it. The goal of this class is to allow simple extension and ultimately, the creation of
 * LevelCreators to be easy. Currently the only abstract method is to create the content which is ultimately the
 * only difference in LevelCreators, what inputs you want to decide in that LevelCreator. So to extend, simply
 * fill in that method, you don't even have to call that method as this abstract class does it in the constructor.
 * @author Matthew Tribby
 */
public abstract class LevelCreator extends TitledPane implements ILevelCreator{
	private VBox content;
	private LevelCreatorHolder parent;
	private String name;
	private LevelData data;
	
	/**
	 * Creates a LevelCreator with basic features
	 * @param i Level Number which will go in title
	 * @param parent The LevelCreatorHolder which will ultimately hold this object
	 * @param data The LevelData that will be set by this LevelCreators
	 */
	public LevelCreator(int i, LevelCreatorHolder parent, LevelData data) {
		this(Integer.toString(i), parent, data);
	}
	
	/**
	 * Creates a LevelCreator with basic features 
	 * @param s Title of level
	 * @param parent LevelCreatorHolder which will hold this object
	 * @param data LevelData to be set
	 */
	public LevelCreator(String s, LevelCreatorHolder parent, LevelData data){
		super();
		this.parent = parent;
		this.name = s;
		this.data = data;
		this.setText(s);
		createContent();
	}
	
	/**
	 * This is the only abstract method in this class. This is where the extender will need to build the
	 * necessary GUI components that will take in and set LevelData accordingly. Do not call method in concrete
	 * classes' constructor, it is done for you in this class's constructor.
	 */
	public abstract void createContent();
	
	/**
	 * Adds node to the GUI component
	 */
	public void addToContent(Node node){
		content.getChildren().add(node);
	}
	
	/**
	 * Gets the current content. It has been set in a VBox in this abstract class. We enforced the idea of a VBox
	 * for this implementation because we wanted the caller of this method to be able to access the children, something
	 * that is not possible without casting with the more general Node 
	 */
	public VBox getCurrentContent(){
		return content;
	}
	
	/**
	 * Remove this LevelCreator from its holder
	 */
	public void remove(){
		parent.remove(this);
	}
	
	/**
	 * Returns the name of the Level 
	 * @return level name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Gets the LevelData that this LevelCreator is setting
	 * @return LevelData
	 */
	public LevelData getData(){
		return data;
	}
	
	/**
	 * Overriding of the equals method, compares based on level title
	 * @param Object to compare
	 */
	@Override
	public boolean equals(Object other){
		return(other instanceof LevelCreator && ((LevelCreator) other).getName().equals(this.name));
	}
}
