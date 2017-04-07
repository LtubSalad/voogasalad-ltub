package gameDevelopmentInterface;

import java.util.LinkedList;
import java.util.Queue;
import javafx.util.Pair;

public class Path {
	private Queue<Pair<Integer, Integer>> myPath = new LinkedList<Pair<Integer, Integer>>();
	private Pair<Integer, Integer> currCoord;
	private Pair<Integer, Integer> nextCoord;
	
	public Path() {
		makeDefaultPath();
		//test();
	}
	
	/**
	 * Not sure if the back end will actually need this anymore
	 * @return the path
	 */
	public Queue<Pair<Integer, Integer>> getPath() {
		return myPath;
	}
	
	public void changePath(Queue<Pair<Integer, Integer>> replacementPath) {
		System.out.println("New path: " + replacementPath);
		myPath = replacementPath;
		currCoord = myPath.poll();
		nextCoord = myPath.poll();
	}
	
	public Pair<Pair<Integer, Integer>, Integer> getNextState() {
		return new Pair<Pair<Integer, Integer>, Integer>(getNextCoordinate(), getNextHeading());
	}
	
	private Pair<Integer, Integer> getNextCoordinate() {
		return myPath.peek();
	}
	
	private Integer getNextHeading() {
		//next coord is above where the monster currently is
		if (currCoord.getValue() > nextCoord.getValue() &&
				currCoord.getKey() == nextCoord.getKey()) {
			updateCoords();
			return 0;
		} // next coord is left of where the monster currently is 
		else if (currCoord.getValue() == nextCoord.getValue() &&
				currCoord.getKey() > nextCoord.getKey()) {
			updateCoords();
			return 90;
		} // next coord is right of where the monster currently is 
		else if (currCoord.getValue() == nextCoord.getValue() &&
				currCoord.getKey() < nextCoord.getKey()) {
			updateCoords();
			return 270;
		} // next coord is below where the monster currently is 
		else {
			updateCoords();
			return 180;
		}
	}
	
	private void updateCoords() {
		currCoord = nextCoord;
		nextCoord = myPath.poll();
	}
	
	private void makeDefaultPath() {
		myPath.add(new Pair<Integer, Integer>(0,0));
		myPath.add(new Pair<Integer, Integer>(1,0));
		myPath.add(new Pair<Integer, Integer>(1,1));
		myPath.add(new Pair<Integer, Integer>(0,1));
		myPath.add(new Pair<Integer, Integer>(0,2));
		currCoord = myPath.poll();
		nextCoord = myPath.poll();		
	}
	
//	private void test() {
//		for (int i = 0; i < 4; i++) {
//			System.out.println("currCoord: " + currCoord);
//			System.out.println("nextCoord: " + nextCoord);
//			System.out.println("Heading: " + getNextHeading());
//			System.out.println();
//		}
//	}
	
}
