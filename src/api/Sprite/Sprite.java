package api.Sprite;


import javafx.geometry.Point2D;

public class Sprite implements ISprite {

	public boolean isSelected;
	private IMover mover;
	private IAttacker attacker;

	public Sprite(){
		mover = new Mover();
		attacker = new Attacker();
	}
	
	
	@Override
	public void setAttribute(String attributeType) {
		
	}

	//This method will be handled differently in the future;
	public boolean isLandTile() {
		return false;
	}
	
	public void move(Point2D point){
		mover.move(point.getX(), point.getY());
	}

	public boolean isTargetable() {
		return false;
	}

	public void attack(Sprite target) {
		attacker.attack(target);
	}

	public Point2D getLocation() {
		return new Point2D(0, 0);
	}


	public boolean isAttacked() {
		return false;
	}


}
