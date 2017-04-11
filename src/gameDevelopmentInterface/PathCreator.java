package gameDevelopmentInterface;

import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
/**
 * 
 * @author Jake
 *
 */
public class PathCreator {
	private Path myPath;
	private Queue<Pair<Integer, Integer>> replacementPath = new LinkedList<Pair<Integer, Integer>>();
	private ScreenModelCreator myScreenModel;
	
	public PathCreator(ScreenModelCreator smc) {
		myScreenModel = smc;
		myPath = new Path();
	}
	
	public Queue<Pair<Integer, Integer>> getReplacementPath() {
		return replacementPath;
	}
	
	public void makePath() {
		ScreenMap target = myScreenModel.getScreen();
		target.setOnMouseEntered(e -> target.getGrid().setGridLinesVisible(true));
		target.setOnMouseExited(e -> target.getGrid().setGridLinesVisible(false));
		target.setOnMousePressed(e -> targetSetOnMousePressed(target, e));
	}
	
	private void targetSetOnMousePressed(ScreenMap target, MouseEvent e) {
		double mouseX = e.getScreenX();
		double mouseY = e.getScreenY();
		Pair<Integer, Integer> coords = target.getCoordOfMouseHover(mouseX, mouseY);
		if (!replacementPath.contains(coords)) {
			replacementPath.add(coords);
		}
		e.consume();
	}
	
	public void replacePath() {
		if (isValidPath(new LinkedList<Pair<Integer, Integer>>(replacementPath))) {
			myPath.changePath(replacementPath);
		}
	}

	private boolean isValidPath(Queue<Pair<Integer, Integer>> possiblePath) {
		int numCols = myScreenModel.getScreen().getNumCols();
		int numRows = myScreenModel.getScreen().getNumRows();
		Pair<Integer, Integer> currCoord = possiblePath.poll();
		int index = 0;
		while (possiblePath.size() > 1) {
			if (currCoord.getKey() < 0 ||
					currCoord.getValue() < 0 ||
					currCoord.getKey() > numCols ||
					currCoord.getValue() > numRows) {
				return false;
			}
			if (index == 0 && !atBoundary(currCoord, numCols, numRows)) {
				return false;
			}
			if (!possibleTransition(currCoord, possiblePath.peek())) {
				return false;
			}
			currCoord = possiblePath.poll();
			index += 1;
		}
		if (!atBoundary(possiblePath.poll(), numCols, numRows)) {
			return false;
		}
		return true;
	}
	
	private boolean atBoundary(Pair<Integer, Integer> coord, int numCols, int numRows) {
		return (coord.getKey() == 0 || coord.getKey() == numCols-1 || coord.getValue() == 0 || coord.getValue() == numRows-1);
	}
	private boolean possibleTransition(Pair<Integer, Integer> currCoord, Pair<Integer, Integer> nextCoord) {
		return ((nextCoord.getKey() == currCoord.getKey() && nextCoord.getValue()+1 == currCoord.getValue()) ||
				(nextCoord.getKey() == currCoord.getKey() && nextCoord.getValue()-1 == currCoord.getValue()) ||
				(nextCoord.getKey()-1 == currCoord.getKey() && nextCoord.getValue() == currCoord.getValue()) ||
				(nextCoord.getKey()+1 == currCoord.getKey() && nextCoord.getValue() == currCoord.getValue()));
	}
	
}