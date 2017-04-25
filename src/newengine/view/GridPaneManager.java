/**
 * 
 */
package newengine.view;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * @author Zhiyong
 * The gridpane in the bottom of the BorderPane
 * It contains the selected sprite, all the sprites 
 * and the corresponding skills of the selected sprites
 *
 */
public class GridPaneManager implements NodeManager<Node>{

	@Override
	public Node getNode() {
		// TODO Auto-generated method stub
		GridPane grid = new GridPane();
		grid.setMinSize(30, 500);
		grid.setHgap(5);
		grid.add(getSmallMap(), 0, 0);
		grid.add(getSelectedSpriteButton(), 1, 0);
		grid.add(getAllSpritesButton(), 2, 0);
		grid.add(getSkillsButton(), 3, 0);
		
		return grid;
	}

	private Node getSkillsButton() {
		// TODO Auto-generated method stub
		return null;
	}

	private Node getAllSpritesButton() {
		// TODO Auto-generated method stub
		return null;
	}

	private Node getSelectedSpriteButton() {
		// TODO Auto-generated method stub
		return null;
	}

	private Node getSmallMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
