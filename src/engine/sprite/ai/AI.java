package engine.sprite.ai;

import java.util.Queue;

import data.AttributeData;
import engine.camera.GamePoint;
import engine.sprite.Attribute;
import gameDevelopmentInterface.Path;

public class AI implements Attribute {

	public enum AIType {
		PathFollower, TargetedMover
	}

	private AIType type;
	// TODO create util class for Path
	private Path path;
	private GamePoint currentDest;
	private GamePoint finalDest = null;

	public AI(AttributeData data) {
//		this.path = data.getVariable("path");
//		this.type = type; //TODO set AI type with AttributeData
		currentDest = path.getPath().poll(); //TODO make sure its GamePoint

	}

	public AI(Path path){
		this.path = path;
		currentDest = path.getPath().poll();
	}
	
	public GamePoint getCurrentDest(){
		return currentDest;
	}
	
	public void setPath(Path path){
		this.path = path;
	}

	public void updateCurrentDest(){
		if (!isFinalDest()){
			currentDest = path.getPath().poll();
		} else {
			finalDest = currentDest;
		}
	}

	public AIType getAIType(){
		return type;
	}

	public boolean isFinalDest() {
		return path.getPath().isEmpty();
	}

	public GamePoint getFinalDest(){
		return finalDest;
	}

}
