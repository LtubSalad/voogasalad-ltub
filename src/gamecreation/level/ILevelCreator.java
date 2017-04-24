package gamecreation.level;

import javafx.scene.Node;

public interface ILevelCreator {
	public void createContent();
	public void addToContent(Node node);
	public Node getCurrentContent();
}
