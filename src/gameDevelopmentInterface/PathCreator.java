package gameDevelopmentInterface;

import java.util.LinkedList;
import java.util.Queue;
import commons.point.GamePoint;
import data.DeveloperData;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * Starts the ability for the user to define a path by clicking
 * on coordinates on the grid held in ScreenMap
 * @author Jake
 *
 */
public class PathCreator extends BorderPane {
	private static final double close_bounds = 0.0001;
	private Queue<GamePoint> myPath;
	private Queue<GamePoint> replacementPath = new LinkedList<GamePoint>();
	private DeveloperData myDeveloperData;
	private ScreenMap target;
	
	public PathCreator(DeveloperData developerData) {
		myDeveloperData = developerData;
		clearPath();
	}
	public ScreenMap getScreen() {
		return target;
	}
	public DeveloperData getDeveloperData() {
		return myDeveloperData;
	}
	public void clearPath() {
		this.getChildren().clear();
		//myPath.getPath().clear();
		target = new ScreenMap(myDeveloperData);
		target.resize(350, 350);
		this.setCenter(target);
		this.setRight(new PathButtonsPanel(myDeveloperData, this, target));
	}
	
	/**
	 * 
	 * @return the replacement path
	 */
	public Queue<GamePoint> getReplacementPath() {
		return replacementPath;
	}
	/**
	 * Called by the buttons panel
	 */
	public void makePath() {
		Queue<GamePoint> newPath = new LinkedList<GamePoint>();
		target.setOnMouseEntered(e -> target.getGrid().setGridLinesVisible(true));
		target.setOnMouseExited(e -> target.getGrid().setGridLinesVisible(false));
		target.setOnMouseDragged(e -> targetSetOnMouseDragged(target, e, newPath));
		target.setOnMouseReleased(e -> replacePath(newPath));
	}
	
	/**
	 * Called by the buttons panel
	 */
	public void replacePath(Queue<GamePoint> newPath) {
		myPath = newPath;
	}
	
	public Queue<GamePoint> getPath() {
		return myPath;
	}
	
	private void targetSetOnMouseDragged(ScreenMap target, MouseEvent e, Queue<GamePoint> newPath) {
		Point2D point = target.sceneToLocal(e.getSceneX(), e.getSceneY());
		double mouseX = point.getX();
		double mouseY = point.getY();
		GamePoint coords = target.getCoordOfMouseHover(mouseX, mouseY);
		GamePoint actualGameLocation = target.getActualLocationOfSprite(coords);
		if (!coordAlreadyInPath(actualGameLocation, newPath)) {
			newPath.add(actualGameLocation);
			target.addBorderToCoordinate(coords);
		}
		
		e.consume();
	}
	
	private boolean coordAlreadyInPath(GamePoint coords, Queue<GamePoint> path) {
		double testX = coords.x();
		double testY = coords.y();
		// in a for-loop so returning true is a clean way to handle this logic
		for (GamePoint gp : path) {
			double alreadyX = gp.x();
			double alreadyY = gp.y();
			if (Math.abs(testX - alreadyX) < close_bounds && Math.abs(testY - alreadyY) < close_bounds) {
				return true;
			}
		}
		return false;
	}
}
