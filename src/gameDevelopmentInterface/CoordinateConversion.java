package gameDevelopmentInterface;

import engine.camera.GamePoint;
import javafx.util.Pair;

public class CoordinateConversion {
	
	public CoordinateConversion() {
		
	}
	
	public GamePoint fromPairToGamePoint(Pair<Integer, Integer> pairCoord) {
		return new GamePoint(pairCoord.getKey(), pairCoord.getValue());
	}
	
	public Pair<Integer, Integer> fromGamePointToPair(GamePoint gameCoord) {
		Double dx = gameCoord.x();
		Double dy = gameCoord.y();
		Integer x = dx.intValue();
		Integer y = dy.intValue();
		return new Pair<Integer, Integer>(x, y);
	}

}
