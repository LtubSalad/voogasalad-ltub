package newengine.utils.checker;

import java.util.List;
import java.util.stream.Collectors;

import newengine.sprite.Sprite;
import newengine.sprite.components.RangeDetector;
import newengine.utils.SpriteUtils;

/**
 * Check if one sprite is in the detection range of another sprite.
 * 
 * @author Yilin Gao, Keping Wang
 *
 */
public class RangeChecker {

	private RangeChecker() { }
	
	public static List<Sprite> spritesInRange(Sprite detector, List<Sprite> sprites) {
		double range = detector.getComponent(RangeDetector.TYPE).get().range();
		List<Sprite> detectees = sprites.stream().filter((s) -> {
			return (s != detector && SpriteUtils.dist(detector, s) <= range);
		}).collect(Collectors.toList());
		return detectees;
	}

}
