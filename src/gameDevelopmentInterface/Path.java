package gameDevelopmentInterface;

import java.util.LinkedList;
import java.util.Queue;

import commons.point.GamePoint;
import javafx.util.Pair;
/**
 * This is a path object that will be associated with all monster sprites
 * so that they can know the points to which they are supposed to go.
 * @author Jake
 *
 */
public class Path {
	private Queue<GamePoint> myPath = new LinkedList<GamePoint>();
	private GamePoint currCoord;
	private GamePoint nextCoord;
	private String pathName;
	
	public Path() {
		this("A Nameless Path");
		//makeDefaultPath();
	}
	
	public Path(String name) {
		this(name, new LinkedList<GamePoint>());
	}
	
	public Path(String name, Queue<GamePoint> path) {
		this.pathName=name;
		myPath = path;
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
		if (isAbove()) {
			updateCoords();
			return 0;
		} 
		else if (isLeft()) {
			updateCoords();
			return 90;
		}
		else if (isRight()) {
			updateCoords();
			return 270;
		} else {
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
//		myPath.add(new GamePoint(500,200));
//		myPath.add(new GamePoint(100,0));
//		myPath.add(new GamePoint(100,100));
//		myPath.add(new GamePoint(0,300));
//		myPath.add(new GamePoint(300,300));

		
//		myPath.add(new GamePoint(0,0));
//		myPath.add(new GamePoint(0,100));
//		myPath.add(new GamePoint(100,100));
//		myPath.add(new GamePoint(100,0));
//		myPath.add(new GamePoint(0, 0));
		
//		myPath.add(new GamePoint(0,0));
//		myPath.add(new GamePoint(0,0.9));
//		myPath.add(new GamePoint(0.9,0.9));
//		myPath.add(new GamePoint(0.9,0));
//		myPath.add(new GamePoint(0, 0));
	
	}	

	public String getName(){
		return pathName;
	}
}