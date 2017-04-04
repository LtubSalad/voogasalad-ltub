package sprite;

public class Sprite {
	private MoverAttribute mover;
	private AttackerAttribute attacker;
	
	public Sprite(){
		mover = new StationaryMoverAttribute();
		attacker = new MeleeAttackerAttribute();
	}
	
}
