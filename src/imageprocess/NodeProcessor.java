/**
 * 
 */
package imageprocess;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;

/**
 * @author Zhiyong
 *
 */
public class NodeProcessor {
	
	/**
	 * @param node
	 * @return
	 * Given any javafx node, return the image on the node
	 */
	BufferedImage getImage(Node node){
		WritableImage snapshot = node.snapshot(new SnapshotParameters(), null);
		BufferedImage buffImg= SwingFXUtils.fromFXImage(snapshot, null);
		return buffImg;
		
	}
	
	double getAngle(Node node){
		double xx = node.getLocalToSceneTransform().getMxx();
		double xy = node.getLocalToSceneTransform().getMxy();
		double angle = Math.atan2(-xy, xx);
		
		angle = Math.toDegrees(angle);
		//angle = angle < 0 ? angle +360 : angle;
		return angle;
		
	}

}
