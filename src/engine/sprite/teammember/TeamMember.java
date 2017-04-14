package engine.sprite.teammember;

import data.AttributeData;
import engine.sprite.Attribute;

/**
 * Attribute which decides what team a sprite is on. Important for any sort of game where there will be groups of sprite
 * that are on the same team (i.e. so they don't attack each other)
 *
 */
public class TeamMember implements Attribute {
	private int teamNum;

//	public TeamMember(AttributeData data){
//		this.teamNum = Integer.parseInt(data.getVariable("number"));
//	}
	
	/**
	 * Initializes the team number to a value
	 * @param number
	 */
	public TeamMember(int number){
		this.teamNum = number;
	}
	
	/**
	 * Returns the current team number
	 * @return team number
	 */
	public int getTeamNum(){
		return teamNum;
	}
	
	/**
	 * Sets the team number
	 * @param team number
	 */
	public void setTeamNum(int t){
		teamNum = t;
	}

}
