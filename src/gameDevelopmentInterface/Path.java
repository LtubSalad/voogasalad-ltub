package gameDevelopmentInterface;

import java.util.LinkedList;
import java.util.Queue;

import engine.camera.GamePoint;
import javafx.util.Pair;
/**
 * 
 * @author Jake
 *
 */
public class Path {
	private Queue<GamePoint> myPath = new LinkedList<GamePoint>();
	private GamePoint currCoord;
	private GamePoint nextCoord;
	
	public Path() {
		makeDefaultPath();
	}
	
	/**
	 * Not sure if the back end will actually need 
	 * this anymore but returns the actual path
	 * @return the path
	 */
	public Queue<GamePoint> getPath() {
		return myPath;
	}
	/**
	 * Called when path is replaced by user clicking Save/Check path button
	 * @param replacementPath
	 */
	public void changePath(Queue<GamePoint> replacementPath) {
		myPath = replacementPath;
	}
	/**
	 * 
	 * @return a pair of the next coordinate and the heading
	 */
	public Pair<GamePoint, Integer> getNextState() {
		return new Pair<GamePoint, Integer>(getNextCoordinate(), getNextHeading());
	}
	
	private GamePoint getNextCoordinate() {
		return myPath.peek();
	}
	
	private Integer getNextHeading() {
		//next coord is above where the monster currently is
		if (isAbove()) {
			updateCoords();
			return 0;
		} // next coord is left of where the monster currently is 
		else if (isLeft()) {
			updateCoords();
			return 90;
		} // next coord is right of where the monster currently is 
		else if (isRight()) {
			updateCoords();
			return 270;
		} // next coord is below where the monster currently is 
		else {
			updateCoords();
			return 180;
		}
	}

	private boolean isRight() {
		return currCoord.y() == nextCoord.y() &&
				currCoord.x() < nextCoord.x();
	}

	private boolean isLeft() {
		return currCoord.y() == nextCoord.y() &&
				currCoord.x() > nextCoord.x();
	}

	private boolean isAbove() {
		return currCoord.y() > nextCoord.y() &&
				currCoord.x() == nextCoord.x();
	}
	
	private void updateCoords() {
		currCoord = nextCoord;
		nextCoord = myPath.poll();
	}
	
	private void makeDefaultPath() {
		myPath.add(new GamePoint(500,200));
		myPath.add(new GamePoint(100,0));
		myPath.add(new GamePoint(100,100));
		myPath.add(new GamePoint(0,300));
		myPath.add(new GamePoint(300,300));
	
	}
	

	
}