package api.Sprite;


import javafx.geometry.Point2D;

public class Sprite implements ISprite {

	public boolean isSelected;

	@Override
	public void setAttribute(String attributeType) {
		
	}

	public void onAttacked(Object e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isAttacked(){
		return true; 
	}

	public boolean isLandTile() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void move(Point2D point2d){
		
	}

	public boolean isTargetable() {
		return false;
	}

	public void attack(Sprite target) {
		
	}



}
