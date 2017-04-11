package gameDevelopmentInterface;

import java.util.LinkedList;
import java.util.Queue;
import javafx.util.Pair;
/**
 * 
 * @author Jake
 *
 */
public class Path {
	private Queue<Pair<Integer, Integer>> myPath = new LinkedList<Pair<Integer, Integer>>();
	private Pair<Integer, Integer> currCoord;
	private Pair<Integer, Integer> nextCoord;
	
	public Path() {
		makeDefaultPath();
		//test();
	}
	
	public Queue<Pair<Integer, Integer>> getStartAndEndAndTurningPoints() {
		Queue<Pair<Integer, Integer>> startEndAndTurns = new LinkedList<Pair<Integer, Integer>>();
		Queue<Pair<Integer, Integer>> copyOfMyPath = new LinkedList<Pair<Integer, Integer>>(myPath);
		copyOfMyPath.poll(); //remove first element to offset the index
		int idx = 0;
		Integer currHeading = 0;
		Integer nextHeading = 0;
		for (Pair<Integer, Integer> currCoord : myPath) {
			Pair<Integer, Integer> nextCoord = copyOfMyPath.peek();
			//getNextHeading(Pair<Integer, Integer> currCoord, Pair<Integer, Integer> nextCoord)
			if (idx == 0 || idx == myPath.size()) {
				startEndAndTurns.add(currCoord);
			} else if (currHeading != nextHeading) {
				startEndAndTurns.add(currCoord);
			}
			idx += 1;
		}
		return myPath;
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
	
	private Integer getNextHeading(Pair<Integer, Integer> currCoord, Pair<Integer, Integer> nextCoord) {
		//next coord is above where the monster currently is
		if (isAbove(currCoord, nextCoord)) {
			updateCoords();
			return 0;
		} // next coord is left of where the monster currently is 
		else if (isLeft(currCoord, nextCoord)) {
			updateCoords();
			return 90;
		} // next coord is right of where the monster currently is 
		else if (isRight(currCoord, nextCoord)) {
			updateCoords();
			return 270;
		} // next coord is below where the monster currently is 
		else if (isBelow(currCoord, nextCoord)){
			updateCoords();
			return 180;
		}
		return 0;
	}
	
	private boolean isRight(Pair<Integer, Integer> currCoord, Pair<Integer, Integer> nextCoord) {
		return currCoord.getValue() == nextCoord.getValue() &&
				currCoord.getKey() < nextCoord.getKey();
	}
	
	private boolean isLeft(Pair<Integer, Integer> currCoord, Pair<Integer, Integer> nextCoord) {
		return currCoord.getValue() == nextCoord.getValue() &&
				currCoord.getKey() > nextCoord.getKey();
	}
	
	private boolean isAbove(Pair<Integer, Integer> currCoord, Pair<Integer, Integer> nextCoord) {
		return currCoord.getValue() > nextCoord.getValue() &&
				currCoord.getKey() == nextCoord.getKey();
	}
	
	private boolean isBelow(Pair<Integer, Integer> currCoord, Pair<Integer, Integer> nextCoord) {
		return currCoord.getValue() < nextCoord.getValue() &&
				currCoord.getKey() == nextCoord.getKey();
	}

	private boolean isRight() {
		return currCoord.getValue() == nextCoord.getValue() &&
				currCoord.getKey() < nextCoord.getKey();
	}

	private boolean isLeft() {
		return currCoord.getValue() == nextCoord.getValue() &&
				currCoord.getKey() > nextCoord.getKey();
	}

	private boolean isAbove() {
		return currCoord.getValue() > nextCoord.getValue() &&
				currCoord.getKey() == nextCoord.getKey();
	}
	
	private boolean isBelow() {
		return currCoord.getValue() < nextCoord.getValue() &&
				currCoord.getKey() == nextCoord.getKey();
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
	
}