package gameDevelopmentInterface;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class Path {
	private List<Pair<Integer, Integer>> myPath = new ArrayList<Pair<Integer, Integer>>();
	
	public Path() {
		makeDefaultPath();
	}
	
	public List<Pair<Integer, Integer>> getPath() {
		return myPath;
	}
	
	public void changePath(List<Pair<Integer, Integer>> replacementPath) {
		myPath = replacementPath;
		System.out.println("Replacement path: " + replacementPath);
	}
	
	private void makeDefaultPath() {
		//put in dummy values of the path
		
	}
	
}
