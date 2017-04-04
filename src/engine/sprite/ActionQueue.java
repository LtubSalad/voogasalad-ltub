package engine.sprite;

import java.util.LinkedList;

public class ActionQueue {

	LinkedList<Action> actions = new LinkedList<>();
	
	public void addAction(Action action) {
		actions.addLast(action);
	}
	
	public boolean isEmpty() {
		return actions.isEmpty();
	}
	
	public void clearQueue() {
		actions.clear();
	}
	
	public void executeNextAction() {
		if (!isEmpty()) {
			actions.removeFirst().execute();
		}
	}
		
}
