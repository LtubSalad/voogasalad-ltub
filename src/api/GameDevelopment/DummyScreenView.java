package api.GameDevelopment;

import javafx.scene.Node;
/**
 * 
 * @author Jake, Daniel
 * Provides a way for the developer to see the sprites that will be added to the screen. Sprites are added
 * through binding.
 *
 */
public interface DummyScreenView {
	/**
	 * @param node
	 * Adds a node to its internal scene, nodes are only called when Sprites are added to the ScreenModel.
	 * 
	 */
	public void addNode(Node node);
}
