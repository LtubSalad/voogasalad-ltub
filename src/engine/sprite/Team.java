package engine.sprite;

import data.AttributeData;

public class Team implements Attribute {
	private int teamNum;

	public Team(AttributeData data){
		//this.teamNum = Integer.parseInt(data.getVariable("number"));
		teamNum = 1;
	}
	
	@Override
	public void switchOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void switchOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean isAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double update(double dt) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getTeamNum(){
		return teamNum;
	}

}
