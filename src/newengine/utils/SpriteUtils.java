package newengine.utils;

import commons.point.GamePoint;
import newengine.sprite.Sprite;
import newengine.sprite.components.Position;

public class SpriteUtils {
	
	private SpriteUtils() { }
	
	public static double dist(Sprite s1, Sprite s2) {
		GamePoint pos1 = s1.getComponent(Position.TYPE).get().pos();
		GamePoint pos2 = s2.getComponent(Position.TYPE).get().pos();
		return pos1.distFrom(pos2);
	}
	

}
