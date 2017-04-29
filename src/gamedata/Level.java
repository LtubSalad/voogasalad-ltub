package gamedata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import newengine.sprite.Sprite;

/**
 * @author tahiaemran
 *
 * this class represents what a general level consists of 
 *
 */
public class Level {
	//TODO: change this class to accomodate what levels actually accomodate 
	
	private List<Sprite> mySprites; 
	// private Path myPath 
	// private Condition myWinningCondition
	// private Condition myLosingCondition
	
	public Level(){
		mySprites = new ArrayList<Sprite>(); 
	}
	
	public void addSprite(Sprite s){
		mySprites.add(s);
	}
	
	public List<Sprite> getSprites(){
		return Collections.unmodifiableList(mySprites); 
	}
	
}
