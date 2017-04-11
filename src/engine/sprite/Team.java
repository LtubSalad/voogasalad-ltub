package engine.sprite;

import data.AttributeData;

public class Team implements Attribute {
	private int teamNum;

	public Team(AttributeData data){
		//this.teamNum = Integer.parseInt(data.getVariable("number"));
		teamNum = 1;
	}
	
	public int getTeamNum(){
		return teamNum;
	}

}
