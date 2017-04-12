package engine.sprite.range;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bus.EventBus;
import engine.camera.GamePoint;
import engine.sprite.Sprite;
import engine.sprite.team.Team;

/**
 * Check if one sprite is in the detection range of another sprite.
 * @author Yilin Gao
 *
 */
public class InRangeChecker {
	
	private EventBus bus;
	
	public InRangeChecker(EventBus bus) {
		this.bus = bus;
	}
	
	/**
	 * Loop over all sprites in the game and detect any in-range between any two sprites.
	 * @param sprites A list of sprites in the model
	 */
	public void checkInRange(List<Sprite> sprites) {
		for (Sprite detector: sprites) {
			for (Sprite detectee: sprites) {
				if (detector == detectee) { continue; }
				if (inRange(detector, detectee)) {
					bus.emit(new InRangeEvent(InRangeEvent.ANY, detector, detectee));
				}
			}
		}
	}
	
	public void checkInRange(List<Sprite> sprites, int a, int b){
		List<Sprite> teamA = new ArrayList<>();
		List<Sprite> teamB = new ArrayList<>();
		for (Sprite s : sprites){
			if (s.getTeam().isPresent()){
				int i = s.getTeam().get().getTeamNum();
				if (i == a){
					teamA.add(s);
				} else if (i == b) {
					teamB.add(s);
				}
			}
		}

		for (Sprite detector: teamA) {
			for (Sprite detectee: teamB) {
				if (detector == detectee) { continue; }
				if (inRange(detector, detectee)) {
					bus.emit(new InRangeEvent(InRangeEvent.ANY, detector, detectee));
				}
			}
		}
	}

	private boolean inRange(Sprite detector, Sprite detectee) {
		GamePoint detectorPos = detector.getPos();
		GamePoint detecteePos = detectee.getPos();
		double distance = detectorPos.distFrom(detecteePos);
		if (distance <= detector.getDetectionRange()) {
			return true;
		}
		return false;
	}

}
