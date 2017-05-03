package gameDevelopmentInterface;

import java.util.LinkedList;
import java.util.Queue;

import commons.point.GamePoint;
import data.DeveloperData;
import data.ScreenModelData;
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
	private Path myPath;
	private Queue<GamePoint> replacementPath = new LinkedList<GamePoint>();
	private DeveloperData myDeveloperData;
	private ScreenMap target;
	
	public PathCreator(DeveloperData developerData) {
		myDeveloperData = developerData;
		myPath = new Path();
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
		myPath.getPath().clear();
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
		target.setOnMouseEntered(e -> target.getGrid().setGridLinesVisible(true));
		target.setOnMouseExited(e -> target.getGrid().setGridLinesVisible(false));
		target.setOnMouseDragged(e -> targetSetOnMouseDragged(target, e));
	}
	
	/**
	 * Called by the buttons panel
	 */
	public void replacePath() {
		if (isValidPath(new LinkedList<GamePoint>(replacementPath))) {
			myPath.changePath(replacementPath);
		}
	}
	
	private void targetSetOnMouseDragged(ScreenMap target, MouseEvent e) {
		Point2D point = target.sceneToLocal(e.getSceneX(), e.getSceneY());
		double mouseX = point.getX();
		double mouseY = point.getY();
		GamePoint coords = target.getCoordOfMouseHover(mouseX, mouseY);
		GamePoint actualGameLocation = target.getActualLocationOfSprite(coords);
		//GamePoint percentLocation = new GamePoint(actualGameLocation.x()/target.getScreenWidth(), actualGameLocation.y()/target.getScreenHeight());
		if (!coordAlreadyInPath(actualGameLocation)) {
			replacementPath.add(actualGameLocation);
			target.addBorderToCoordinate(coords);
		}
		e.consume();
	}
	
	private boolean coordAlreadyInPath(GamePoint coords) {
		double testX = coords.x();
		double testY = coords.y();
		for (GamePoint gp : replacementPath) {
			double alreadyX = gp.x();
			double alreadyY = gp.y();
			if (Math.abs(testX - alreadyX) < close_bounds && Math.abs(testY - alreadyY) < close_bounds) {
				return true;
			}
		}
		return false;
	}

	private boolean isValidPath(Queue<GamePoint> path) {
//		int numCols = myScreenModel.getScreen().getNumCols();
//		int numRows = myScreenModel.getScreen().getNumRows();
//		GamePoint currCoord = path.poll();
//		int index = 0;
//		while (path.size() > 1) {
//			if (outOfBounds(numCols, numRows, currCoord)) {
//				return false;
//			}
//			if (index == 0 && !atBoundary(currCoord, numCols, numRows)) {
//				return false;
//			}
//			if (!possibleTransition(currCoord, path.peek())) {
//				return false;
//			}
//			currCoord = path.poll();
//			index += 1;
//		}
//		if (!atBoundary(path.poll(), numCols, numRows)) {
//			return false;
//		}
		return true;
	}
	private boolean outOfBounds(int numCols, int numRows, GamePoint currCoord) {
		return currCoord.x() < 0 ||
				currCoord.y() < 0 ||
				currCoord.x() > numCols ||
				currCoord.y() > numRows;
	}
	private boolean atBoundary(GamePoint coord, int numCols, int numRows) {
		return (coord.x() == 0 || coord.x() == numCols-1 || coord.y() == 0 || coord.y() == numRows-1);
	}
	private boolean possibleTransition(GamePoint currCoord, GamePoint nextCoord) {
		return ((nextCoord.x() == currCoord.x() && nextCoord.y()+1 == currCoord.y()) ||
				(nextCoord.x() == currCoord.x() && nextCoord.y()-1 == currCoord.y()) ||
				(nextCoord.x()-1 == currCoord.x() && nextCoord.y() == currCoord.y()) ||
				(nextCoord.x()+1 == currCoord.x() && nextCoord.y() == currCoord.y()));
	}
	
}
