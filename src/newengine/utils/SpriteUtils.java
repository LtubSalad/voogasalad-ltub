package newengine.utils;

import commons.point.GamePoint;
import newengine.sprite.Sprite;
import newengine.sprite.components.Position;

public class SpriteUtils {
	
	private static GamePoint pos1 = new GamePoint();
	private static GamePoint pos2 = new GamePoint();
	
	private SpriteUtils() { }
	
	public static double dist(Sprite s1, Sprite s2) {
		
		s1.getComponent(Position.TYPE).ifPresent((position) -> {
			pos1 = s1.getComponent(Position.TYPE).get().pos();
		});
		s2.getComponent(Position.TYPE).ifPresent((position) -> {
			pos2 = s2.getComponent(Position.TYPE).get().pos();
		});
		
		return pos1.distFrom(pos2);
	}
	

}
