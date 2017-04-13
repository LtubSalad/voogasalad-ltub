package gameDevelopmentInterface;

import java.util.LinkedList;
import java.util.Queue;

import engine.camera.GamePoint;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
/**
 * Starts the ability for the user to define a path by clicking
 * on coordinates on the grid held in ScreenMap
 * @author Jake
 *
 */
public class PathCreator {
	private Path myPath;
	private Queue<GamePoint> replacementPath = new LinkedList<GamePoint>();
	private ScreenModelCreator myScreenModel;
	
	public PathCreator(ScreenModelCreator smc) {
		myScreenModel = smc;
		myPath = new Path();
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
		ScreenMap target = myScreenModel.getScreen();
		target.setOnMouseEntered(e -> target.getGrid().setGridLinesVisible(true));
		target.setOnMouseExited(e -> target.getGrid().setGridLinesVisible(false));
		target.setOnMousePressed(e -> targetSetOnMousePressed(target, e));
	}
	/**
	 * Called by the buttons panel
	 */
	public void replacePath() {
		if (isValidPath(new LinkedList<GamePoint>(replacementPath))) {
			myPath.changePath(replacementPath);
		}
	}
	
	private void targetSetOnMousePressed(ScreenMap target, MouseEvent e) {
		double mouseX = e.getScreenX();
		double mouseY = e.getScreenY();
		GamePoint coords = target.getCoordOfMouseHover(mouseX, mouseY);
		if (!replacementPath.contains(coords)) {
			replacementPath.add(coords);
		}
		e.consume();
	}

	private boolean isValidPath(Queue<GamePoint> path) {
		int numCols = myScreenModel.getScreen().getNumCols();
		int numRows = myScreenModel.getScreen().getNumRows();
		GamePoint currCoord = path.poll();
		int index = 0;
		while (path.size() > 1) {
			if (outOfBounds(numCols, numRows, currCoord)) {
				return false;
			}
			if (index == 0 && !atBoundary(currCoord, numCols, numRows)) {
				return false;
			}
			if (!possibleTransition(currCoord, path.peek())) {
				return false;
			}
			currCoord = path.poll();
			index += 1;
		}
		if (!atBoundary(path.poll(), numCols, numRows)) {
			return false;
		}
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