/**
 * 
 */
package imageprocess;

import java.time.Duration;

import javafx.scene.Node;

/**
 * @author Zhiyong
 *
 */
public interface NodeProcessor extends Processor<Node> {
	
	 /**
	 * @param t
	 * @param angle
	 * rotate the element t by the given angle in the second argument
	 * Note here we rotate the nodes that t reside in duration time
	 */
	Node rotate(Node node, double angle, Duration duration);

}
