package gameauthorgui;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * A DeveloperStep is an HBox extension that is connected to a Pane/Node of other sort. The goal of a DeveloperStep
 * is to be a part of a game creation process. So ideally, a series of DeveloperSteps will be chained together in a
 * DeveloperStepOrganizer and that will form the basis for the game creation process. Each DeveloperStep takes in a
 * title which it will display and the Node that will be linked to it and be displayed when it is clicked on.
 * In order to use the DevloperStep framework, you first want to assemble the steps and then add them to a 
 * DeveloperStepOrganizer. Or you could extend the GameAuthor class which already has a DeveloperStep organizer
 * built in and all you have to do is add whatever steps you want to
 * @author Matthew Tribby
 */
public class DeveloperStep extends HBox{
	private Node step;
	private String name;

	/**
	 * Creates a DeveloperStep with the given title and the Node linked with it
	 * @param name
	 * @param step
	 */
	public DeveloperStep(String name, Node step){
		super();
		this.getChildren().add(new Text(name));
		this.step = step;
		this.name = name;
		setBaseColor();
	}
	
	/**
	 * Highlights the HBox, can be used to show if selected or not
	 */
	public void highlight(){
		this.setStyle("-fx-background-color: #ffff00;");
	}
	
	/**
	 * Sets the base color of the HBox
	 */
	public void setBaseColor(){
		this.setStyle("-fx-background-color: #ffffff;");
	}
	
	/**
	 * Gets the Node connected to this step
	 * @return Node
	 */
	public Node getStep(){
		return step;
	}
	
	/**
	 * Get title of the DeveloperStep
	 * @return name of step
	 */
	public String getName(){
		return name;
	}

}
